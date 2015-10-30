package com.meetu.adapter;

import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalBitmap;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;



import com.meetu.R;
import com.meetu.common.ImageLoader;
import com.meetu.entity.Huodong;
import com.meetu.myapplication.MyApplication;
import com.meetu.tools.DensityUtil;


@SuppressLint("NewApi")
public class NewsListViewAdapter  extends BaseAdapter implements OnClickListener {

	private Context mContext;
	private List<Huodong> newsList;
	
	
	private ImageLoader mImageLoader;
	
	private final int TYPE_COUNT=2;
//	private FinalBitmap fianlBitmap;
//	
	
	public NewsListViewAdapter(Context context,List<Huodong> newsList){
		this.mContext=context;
		this.newsList=newsList;

	
//		NewsApplication app=(NewsApplication)context.getApplicationContext();
//		fianlBitmap=app.getFinalBitmap();
	}
	/**
	 * 活动状态 style
	 *  1 表示进行中 2表示已结束
	 */
//	@Override
//	public int getItemViewType(int position) {
//		
//		// TODO Auto-generated method stub
//		return newsList!=null?newsList.get(position).getStyle():-1;
//	}
	/**
	 * 
	 */
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return TYPE_COUNT;
	}



	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.d("lucifer","getCount()");
//		return newsList.size();
		return newsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		Log.d("lucifer","getItem()");
		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Log.d("lucifer","getItemId()");
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		
		ViewHolder holder=null;
		Huodong item=newsList.get(position);
		
		if(convertView==null){
			holder=new ViewHolder();
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_huodong_fragment, null);
			holder.ivImgUrl=(ImageView)convertView.findViewById(R.id.img_huodong_homepage);
			holder.tvTilte=(TextView) convertView.findViewById(R.id.title_huodong_homepage);
			holder.styleTextView=(TextView) convertView.findViewById(R.id.style_homepage_tv);
			holder.topRl=(RelativeLayout) convertView.findViewById(R.id.top_homepage_item);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}

		Log.d("lucifer","getView()");
//		RelativeLayout.LayoutParams params=(LayoutParams) holder.topRl.getLayoutParams();
//		if(position==0){
//			params.topMargin=DensityUtil.dip2px(mContext, 44);
//			holder.topRl.setLayoutParams(params);
//		}
		
		
		holder.ivImgUrl.setImageResource(item.getImg());
		
//		new ImageLoader().showImageByThread(holder.ivImgUrl, item.getImg());
//		mImageLoader.showImageByAsyncTask(holder.ivImgUrl, item.getImg());
		holder.tvTilte.setText(item.getTitle());
		if(item.getStyle()==1){
			holder.styleTextView.setText("活动进行中");
		}if(item.getStyle()==2){
			holder.styleTextView.setText("活动已结束");
			holder.topRl.setBackgroundResource(R.drawable.acty_cover_card_img_mask);
		}
		
		
		return convertView;
	}
	
	
	private class ViewHolder{
		private TextView tvTilte;
		private TextView tvDigest;
		private TextView tvReply;
		private ImageView ivImgUrl;
		private ImageView ivExtImg1;
		private ImageView ivExtImg2;
		private TextView styleTextView;
		private RelativeLayout topRl;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	



}
