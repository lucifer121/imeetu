package com.meetu.activity.miliao;

import java.io.IOException;
import java.util.List;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.entity.ChatEmoji;
import com.meetu.tools.StringToDrawable;
import com.meetu.tools.zhuanhuan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class FaceGVAdapter extends BaseAdapter {
	private static final String TAG = "FaceGVAdapter";
	private List<ChatEmoji> list;
	private Context mContext;

	public FaceGVAdapter(List<ChatEmoji> list, Context mContext) {
		super();
		this.list = list;
		this.mContext = mContext;
	}

	public void clear() {
		this.mContext = null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		log.e("zcq", "list.size=="+list.size());
		return list.size();
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHodler hodler;
		ChatEmoji chatEmoji=new ChatEmoji();
		if (convertView == null) {
			hodler = new ViewHodler();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.face_image, null);
			hodler.iv = (ImageView) convertView.findViewById(R.id.face_img);
			
			convertView.setTag(hodler);
		} else {
			hodler = (ViewHodler) convertView.getTag();
		}
		//			Bitmap mBitmap = BitmapFactory.decodeStream(mContext.getAssets().open("face/png/" + list.get(position)));
//			hodler.iv.setImageBitmap(mBitmap);
		

//		int mDrawable=mContext.getResources().getIdentifier(list.get(position), "drawable", mContext.getPackageName());
		hodler.iv.setImageResource(Integer.parseInt(""+list.get(position).getId()));
//		log.e("lucifer121121221212"+""+list.get(position)+"  mDrawable=="+mDrawable);
//		hodler.iv.setImageBitmap(mBitmap);
		

		return convertView;
	}

	class ViewHodler {
		ImageView iv;
		
	}
}
