package com.meetu.adapter;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.meetu.R;
import com.meetu.activity.mine.FavorListActivity;
import com.meetu.activity.mine.MinephotoActivity;
import com.meetu.entity.PhotoWall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MemoryPhotoAdapter extends PagerAdapter{
	private Context  mContext;
	FinalBitmap finalBitmap;
	private List<PhotoWall> Newslist;
	
	public MemoryPhotoAdapter(Context context, List<PhotoWall> list) {
		super();
		this.mContext = context;
		this.Newslist = list;
		
//		MinephotoActivity activity=(MinephotoActivity)context;
//		NewsApplication app=(NewsApplication)activity.getApplicationContext();
//		finalBitmap=app.getFinalBitmap();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Newslist.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view==object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		//super.destroyItem(container, position, object);r
		container.removeView((View)object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		PhotoWall item=Newslist.get(position);
		View view = LayoutInflater.from(mContext).inflate(R.layout.item_memory_detial,null);
		ImageView img=(ImageView)view.findViewById(R.id.img_memorywall_detial_item);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				((Activity) mContext).finish();
			
				
			}
		});
//		finalBitmap.display(img,news.getImgurl());
		
		img.setImageResource(item.getImg());
//		/**
//		 * viewpager 内部事件监听处理
//		 */
//		ImageView headphoto=(ImageView) view.findViewById(R.id.nameheader_mine_photoview_fullscreen_img);
//		headphoto.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
////				Intent intent=new Intent(mContext,MainActivity.class);
////				intent.putExtra("page", 3);
////				mContext.startActivity(intent);
//				((Activity) mContext).finish();
//				
//				
//			}
//		});
//		RelativeLayout favorLayout=(RelativeLayout) view.findViewById(R.id.favor_minephoto_mine_rl);
//		favorLayout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
////				Log.e("1", "1111111");
//				Intent intent=new Intent(mContext,FavorListActivity.class);
//				mContext.startActivity(intent);
//				
//				
//			}
//		});
//		
		
		//View view2=LayoutInflater.from(mContext).inflate(R.layout.photo_desc_item_layout, null);
		
//		TextView desc=(TextView)view.findViewById(R.id.tv_content_photo_item_desc);
//		desc.setText(news.getNote());
		container.addView(view);
		return view;
	}

}
