package com.meetu.adapter;

import java.util.List;


import com.meetu.R;

import com.meetu.entity.Huodong;
import com.meetu.entity.User;


import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JoinUserAdapter extends BaseAdapter {
	private Context mContext;
	private List<User> joinUsersList;
	
	public JoinUserAdapter(Context context,List<User> joinUsersList){
		this.mContext=context;
		this.joinUsersList=joinUsersList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return joinUsersList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return joinUsersList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		User item=joinUsersList.get(position);
		if(convertView==null){
			holder=new ViewHolder();
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_joinusers, null);
			holder.ivImgUrl=(ImageView) convertView.findViewById(R.id.userPhoto_joinUsers_img);
			holder.ivSex=(ImageView) convertView.findViewById(R.id.sex_joinUsers_img);
			holder.tvName=(TextView) convertView.findViewById(R.id.userName_item_joinUsers_tv);
			holder.tvSchool=(TextView) convertView.findViewById(R.id.school_item_joinUsers_tv);
			holder.tvWeizhi=(TextView) convertView.findViewById(R.id.position_item_joinUser_tv);
			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		holder.ivImgUrl.setImageResource(item.getHeadPhoto());
		holder.tvName.setText(item.getName());
		holder.tvSchool.setText(item.getSchool());
		holder.tvWeizhi.setText(""+position);
		if(item.getSex().equals("女")){
			holder.ivSex.setImageResource(R.drawable.acty_joinlist_img_female);
		}
		return convertView;
	}
	
	private class ViewHolder{
		private TextView tvName;
		private TextView tvSchool;
		private TextView tvWeizhi;
		private ImageView ivImgUrl;
		private ImageView ivSex;
	
	}


	
	

}
