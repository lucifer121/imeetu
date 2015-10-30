package com.meetu.adapter;

import java.util.List;
import java.util.Map;





import com.meetu.R;
import com.meetu.entity.PhotoWall;
import com.meetu.tools.DisplayUtils;




import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PhotoWallAdapter extends BaseAdapter {
	private static LayoutInflater inflater = null;

	private Context context;
	 private List<PhotoWall> list;
//	private List<String> list;
	// private FinalBitmap fianlBitmap;
	private int showPos;
	private ClickTypeCallBack clickTypeCallBack;
	public final int MIN_CLICK_DELAY_TIME = 1000;

	private long lastClickTime = 0;
	private int width;
	
	private GridViewHeightaListener gridViewHeightaListener;
	 
	public interface GridViewHeightaListener {
		/**
		 * 监听高度的变化
		 * @param height
		 */
		public void callBackHeight(int height);
	}
	
	
	public interface ClickTypeCallBack {

		/**
		 * 
		 * 
		 * @param tid
		 */
		public void clickType(String tid);

	}

	public void setGridViewHeightaListener(GridViewHeightaListener gridViewHeightaListener) {
		this.gridViewHeightaListener = gridViewHeightaListener;
	}

	public PhotoWallAdapter(Context context, List<PhotoWall> list) {
		super();
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.list = list;
		width = DisplayUtils.getWindowWidth((Activity)context);
		// NewsApplication app=(NewsApplication)context.getApplicationContext();
		// fianlBitmap=app.getFinalBitmap();
		showPos = 0;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
	public View getView(int position, View converview, ViewGroup parent) {
		// TODO Auto-generated method stub
//		String item = list.get(position);
		ViewHolder holder = null;
		if (converview == null) {
			holder = new ViewHolder();
			
			converview = LayoutInflater.from(context).inflate(
					R.layout.item_photowall_fragment, null);
//			// ����Ϣ
			holder.rlAll = (RelativeLayout) converview.findViewById(R.id.rl_all);
			holder.ivImg=(ImageView)converview.findViewById(R.id.mine_img_loading);
//			holder.favourNumber = (TextView) converview
//					.findViewById(R.id.mine_favourNumber);
//			holder.viewNumber = (TextView) converview
//					.findViewById(R.id.mine_viewNumber);
			converview.setTag(holder);
		} else {
			holder = (ViewHolder) converview.getTag();
		}
		// 获得屏幕尺寸动态设置
		RelativeLayout.LayoutParams lp = 
				new RelativeLayout.LayoutParams(width/2, LayoutParams.WRAP_CONTENT);
		
		 holder.rlAll.setLayoutParams(lp);
		// holder.viewNumber.setText(item.getViewNumber());
		// fianlBitmap.display(holder.img, item.getImage());
		 PhotoWall item=list.get(position);
		 holder.ivImg.setImageResource(item.getImg());
		return converview;
	}

	public void setListViewHeightBasedOnChildren(GridView listView) {
		  ListAdapter listAdapter = listView.getAdapter();
		  if (listAdapter == null) {
		   // pre-condition
		   return;
		  }
		  int totalHeight = 0,count = listAdapter.getCount()%2==0?listAdapter.getCount()/2:listAdapter.getCount()/2+1;
		  for (int i = 0; i < count; i++) {
		   View listItem = listAdapter.getView(i, null, listView);
		   listItem.measure(0, 0);
		   totalHeight += listItem.getMeasuredHeight();
		  }
		  ViewGroup.LayoutParams params = listView.getLayoutParams();
		  params.height = totalHeight;
		  this.gridViewHeightaListener.callBackHeight(totalHeight);
		  listView.setLayoutParams(params);
		 }
	
	private class ViewHolder {
		private RelativeLayout rlAll;
		private TextView favourNumber;
		private TextView viewNumber;
		private ImageView ivImg;
	}
}
