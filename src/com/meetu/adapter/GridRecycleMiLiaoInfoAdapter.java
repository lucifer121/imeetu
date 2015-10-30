package com.meetu.adapter;

import java.util.List;

import com.meetu.R;
import com.meetu.activity.miliao.MiLiaoInfoActivity;
import com.meetu.adapter.StaggeredMemoryWallAdapter.MyViewHolder;
import com.meetu.adapter.StaggeredMemoryWallAdapter.OnItemClickCallBack;
import com.meetu.entity.PhotoWall;
import com.meetu.entity.User;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GridRecycleMiLiaoInfoAdapter extends RecyclerView.Adapter<GridRecycleMiLiaoInfoAdapter.MyViewHolder>{

	private List<User> list;
	private LayoutInflater mInflater;
	private Context mContext;
	
	
	/**
	 * 单击 和长按接口
	 * @author Administrator
	 *
	 */
	public interface OnMiLiaoInfoItemClickCallBack {
		void onItemClick(int id);

		void onItemLongClick(View view, int position);
	}

	private OnMiLiaoInfoItemClickCallBack mOnItemClickLitener;

	
	public void setOnItemClickLitenerBack(OnMiLiaoInfoItemClickCallBack mOnItemClickLitener) {
		this.mOnItemClickLitener = mOnItemClickLitener;
	}
	
	
public GridRecycleMiLiaoInfoAdapter(Context context, List<User> list) {
		
		mInflater = LayoutInflater.from(context);
		this.list = list;
		this.mContext=context;

	}

	@Override
	public void onBindViewHolder(MyViewHolder holder,final int position) {
		if (list!=null && list.size()>0){
			
			User item = list.get(position);
			holder.tvName.setText(item.getName());
			holder.ivImg.setImageResource(item.getHeadPhoto());
			

			if(item.getIsDetele()==false){
				holder.ivDetele.setVisibility(View.INVISIBLE);
			}else{
				holder.ivDetele.setVisibility(View.VISIBLE);
			}
			
			if (mOnItemClickLitener != null) {
				holder.rlAll.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						mOnItemClickLitener.onItemClick(position);
					}
				});
				
				holder.rlAll.setOnLongClickListener(new OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
	
						return false;
					}
				});
			}
			
		}
		
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		MyViewHolder holder = new MyViewHolder(mInflater.inflate(
				R.layout.item_miliao_info, parent, false));
		
		return holder;
	}
	
	class MyViewHolder extends ViewHolder {
		private RelativeLayout rlAll;
		private ImageView ivImg;
		private ImageView ivDetele;
		private ImageView ivFavor;
		private TextView tvName;

		int id;
		public MyViewHolder(View view) {
			super(view);
			ivImg = (ImageView) view.findViewById(R.id.photo_item_miliao_info_img);
			tvName=(TextView) view.findViewById(R.id.name_item_miliao_info_tv);
			rlAll=(RelativeLayout) view.findViewById(R.id.all_item_miliao_info_rl);
			ivDetele=(ImageView) view.findViewById(R.id.delete_item_miliao_info_img);
			ivFavor=(ImageView) view.findViewById(R.id.favor_item_miliao_info_img);
			}

		
	}
	

}
