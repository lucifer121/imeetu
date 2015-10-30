package com.meetu.common;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.R.integer;
import android.R.string;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

public class ImageLoader {
	private ImageView mImageView; 
	
	//创建cache
	private LruCache<String, Bitmap> mCache;
	
	public ImageLoader(){
		//获取最大可用内存
		int maxMemory=(int) Runtime.getRuntime().maxMemory();
		int cacheSize=maxMemory/8;
		mCache=new LruCache<String, Bitmap>(cacheSize){

			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
			//	return super.sizeOf(key, value);
				
				//在每次存入缓存的时候调用
				return value.getByteCount();
			}
			
		};
	}
	
	//增加到缓存
	public void addBitmapToCache(String url,Bitmap bitmap){
		if(getBitmapFromBitmapCache(url)==null){
			mCache.put(url, bitmap);
		}
		
	}
	
	//从缓存中读取bitmap
	public Bitmap getBitmapFromBitmapCache(String url){
		
		return mCache.get(url);
	}

	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
		super.handleMessage(msg);
		
		mImageView.setImageBitmap((Bitmap)msg.obj);
		
		}
	};
	
	
	
	
	public void showImageByThread(ImageView imageView,final String  url){
		mImageView=imageView;
		
		 new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
					Bitmap bitmap=getBitmapFromURL(url);
					
					Message message=Message.obtain();
					message.obj=bitmap;
					handler.sendMessage(message);
					
				}
				
			 };
		
	}
	
	public Bitmap getBitmapFromURL(String urlString){
		Bitmap bitmap;
		
		InputStream is = null;
		
		
			try {
				URL url=new URL(urlString);
				HttpURLConnection connection=(HttpURLConnection) url.openConnection();
				is=new BufferedInputStream(connection.getInputStream());
				bitmap=BitmapFactory.decodeStream(is);
				connection.disconnect();
				
				return  bitmap;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		return null;
		
	}
	
	/**
	 * AsyncTask方式加载图片
	 * @param imageView
	 * @param url
	 */
	public void showImageByAsyncTask(ImageView imageView,String url){
		Bitmap bitmap=getBitmapFromBitmapCache(url);
		if(bitmap==null){
			new MyAsyncTask(imageView,url).equals(url);
		}else{
			imageView.setImageBitmap(bitmap);
		}

	}
	
	private class MyAsyncTask extends AsyncTask<String, Void, Bitmap>{
		private ImageView mImageView;
		private String mUrl ;
		public MyAsyncTask(ImageView imageView,String url){
			mImageView=imageView;
			mUrl=url;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			String url=params[0];
			//从网上获取图片
			Bitmap bitmap=getBitmapFromURL(url);
			if(bitmap!=null){
				//将图片存进缓存中
				addBitmapToCache(url, bitmap);
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			// TODO Auto-generated method stub
			super.onPostExecute(bitmap);
			if(mImageView.getTag().equals(mUrl)){
				mImageView.setImageBitmap(bitmap);
			}
			
		}
		
		
	}


}
