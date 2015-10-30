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

public class MiLiaoUsersListAdapter extends BaseAdapter {
	private Context mContext;
	private List<User> usersList;
	
	public MiLiaoUsersListAdapter(Context context,List<User> usersList){
		this.mContext=context;
		this.usersList=usersList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return usersList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return usersList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		User item=usersList.get(position);
		if(convertView==null){
			holder=new ViewHolder();
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_miliao_userslist, null);
			holder.ivImgUrl=(ImageView) convertView.findViewById(R.id.userPhoto_miliao_usersList_img);
			holder.ivSex=(ImageView) convertView.findViewById(R.id.sex_miliao_usersList_img);
			holder.tvName=(TextView) convertView.findViewById(R.id.userName_item_miliao_usersList_tv);
			holder.tvSchool=(TextView) convertView.findViewById(R.id.school_item_miliao_usersList_tv);
			
			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		holder.ivImgUrl.setImageResource(item.getHeadPhoto());
		holder.tvName.setText(item.getName());
		holder.tvSchool.setText(item.getSchool());

		if(item.getSex().equals("女")){
			holder.ivSex.setImageResource(R.drawable.acty_joinlist_img_female);
		}
		return convertView;
	}
	
	private class ViewHolder{
		private TextView tvName;
		private TextView tvSchool;
		
		private ImageView ivImgUrl;
		private ImageView ivSex;
	
	}


	
	

}
