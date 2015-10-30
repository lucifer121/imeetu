package com.meetu.adapter;

import java.util.ArrayList;
import java.util.List;

import com.meetu.R;
import com.meetu.adapter.PhotoWallAdapter.GridViewHeightaListener;
import com.meetu.entity.PhotoWall;
import com.meetu.tools.DisplayUtils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StaggeredHomeAdapter extends
			RecyclerView.Adapter<StaggeredHomeAdapter.MyViewHolder> {

	private List<PhotoWall> mDatas;
	private LayoutInflater mInflater;
	private List<Integer> mHeights;
	private int width;

	// private List<Integer> mHeights;
	private GridViewHeightaListener gridViewHeightaListener;
	 
//	public interface GridViewHeightaListener {
//		/**
//		 * 监听高度的变化
//		 * @param height
//		 */
//		public void callBackHeight(int height);
//	}
//	public void setGridViewHeightaListener(GridViewHeightaListener gridViewHeightaListener) {
//		this.gridViewHeightaListener = gridViewHeightaListener;
//	}

	public interface OnItemClickCallBack {
		void onItemClick(int id);

		void onItemLongClick(View view, int position);
	}

	private OnItemClickCallBack mOnItemClickLitener;

	public void setOnItemClickLitener(OnItemClickCallBack mOnItemClickLitener) {
		this.mOnItemClickLitener = mOnItemClickLitener;
	}

	public StaggeredHomeAdapter(Context context, List<PhotoWall> datas) {
		
		mInflater = LayoutInflater.from(context);
		mDatas = datas;
		width = DisplayUtils.getWindowWidth((Activity)context);
//		 mHeights = new ArrayList<Integer>();
//		 if(mDatas!=null && mDatas.size()>0){
//		 for (int i = 0; i < mDatas.size(); i++)
//		 {
//		 mHeights.add( (int) (400+ Math.random() * 500));
//		 }
//	}
	}

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		MyViewHolder holder = new MyViewHolder(mInflater.inflate(
				R.layout.item_photowall_fragment, parent, false));
		
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		if(mDatas!=null && mDatas.size()>0){
		 LayoutParams lp = holder.ivImg.getLayoutParams();
//		 lp.height = mHeights.get(position);
		 lp.width=(width)/2;
		//
		// holder.ivImg.setLayoutParams(lp);
		// 获得屏幕尺寸动态设置
//				RelativeLayout.LayoutParams lp = 
//						new RelativeLayout.LayoutParams(width/2-26, LayoutParams.WRAP_CONTENT);
				
				 holder.ivImg.setLayoutParams(lp);
		
		PhotoWall item = mDatas.get(position);
		holder.ivImg.setImageResource(item.getImg());
		holder.id = item.getId();
		// 如果设置了回调，则设置点击事件
		if (mOnItemClickLitener != null) {
			holder.itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mOnItemClickLitener.onItemClick(position);
				}
			});

			holder.itemView.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					// int pos = holder.getLayoutPosition();
					// mOnItemClickLitener.onItemLongClick(holder.itemView,
					// pos);
					// removeData(pos);
					return false;
				}
			});
		}
	}
	}


	// public void addData(int position)
	// {
	// mDatas.add(position, "Insert One");
	// mHeights.add( (int) (100 + Math.random() * 300));
	// notifyItemInserted(position);
	// }
	//
	// public void removeData(int position)
	// {
	// mDatas.remove(position);
	// notifyItemRemoved(position);
	// }
	
//	public void setListViewHeightBasedOnChildren(GridView listView) {
//		  ListAdapter listAdapter = listView.getAdapter();
//		  if (listAdapter == null) {
//		   // pre-condition
//		   return;
//		  }
//		  int totalHeight = 0,count = listAdapter.getCount()%2==0?listAdapter.getCount()/2:listAdapter.getCount()/2+1;
//		  for (int i = 0; i < count; i++) {
//		   View listItem = listAdapter.getView(i, null, listView);
//		   listItem.measure(0, 0);
//		   totalHeight += listItem.getMeasuredHeight();
//		  }
//		  ViewGroup.LayoutParams params = listView.getLayoutParams();
//		  params.height = totalHeight;
//		  this.gridViewHeightaListener.callBackHeight(totalHeight);
//		  listView.setLayoutParams(params);
//		 }

	class MyViewHolder extends ViewHolder {
		private RelativeLayout rlAll;
		ImageView ivImg;

		int id;
		public MyViewHolder(View view) {
			super(view);
			
			ivImg = (ImageView) view.findViewById(R.id.mine_img_loading);
			rlAll=(RelativeLayout) view.findViewById(R.id.rl_all);
		}

		public void setData() {

		}
	}

}