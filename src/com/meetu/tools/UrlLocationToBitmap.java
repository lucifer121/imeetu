package com.meetu.tools;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class UrlLocationToBitmap {
	/**
	 * 其中w和h你需要转换的大小
	 * @param path  本地图片的路径
	 * @param w  
	 * @param h
	 * @return
	 */
	public  static Bitmap convertToBitmap(String path, int w, int h) {
		            BitmapFactory.Options opts = new BitmapFactory.Options();
		            // 设置为ture只获取图片大小
		             opts.inJustDecodeBounds = true;
		            opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		            // 返回为空
		            BitmapFactory.decodeFile(path, opts);
		            int width = opts.outWidth;
		            int height = opts.outHeight;
		            float scaleWidth = 0.f, scaleHeight = 0.f;
		            if (width > w || height > h) {
		                 // 缩放
		                 scaleWidth = ((float) width) / w;
		                scaleHeight = ((float) height) / h;
		             }
		            opts.inJustDecodeBounds = false;
		             float scale = Math.max(scaleWidth, scaleHeight);
		            opts.inSampleSize = (int)scale;
		           WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
		             return Bitmap.createScaledBitmap(weak.get(), w, h, true);
	        }
	/**
	 * 其中w和h你需要转换的大小

		path转换为bitmap:上面方法即可；
		imageview获取drawable并转换为 bitmap ：Bitmap bt= ((BitmapDrawable) mImageview.getDrawable()).getBitmap();
		resourceid转换为bitmap：Bitmap bt = BitmapFactory.decodeResource(getResources(), R.drawable.resourceid);
		Drawable转换为bitmap：Bitmap bt= ((BitmapDrawable) Drawable).getBitmap();
		因为BitmapDrawable是继承Drawable，所以可以灵活的转换
	 */

}
