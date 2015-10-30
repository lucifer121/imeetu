package com.meetu.fragment;

import com.meetu.R;
import com.meetu.R.id;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MiliaoChannelFragment extends Fragment {
	//控件相关
	private TextView dismissData;
	private ImageView photoManager;
	private TextView titile;
	private TextView numberAll,numberFavor;
	
private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(view==null){
			view=inflater.inflate(R.layout.fragment_miliao_channel, null);
			initView();
			
		}
		ViewGroup parent=(ViewGroup) view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
		return view;
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		dismissData=(TextView) view.findViewById(R.id.date_top_miliao_channel_tv);
		photoManager=(ImageView) view.findViewById(R.id.photoHead_manage_miliao_channel_img);
		titile=(TextView) view.findViewById(R.id.content_center_miliao_channel_tv);
		numberAll=(TextView) view.findViewById(R.id.numberAll_miliao_channel);
		numberFavor=(TextView) view.findViewById(R.id.numberFavor_miliao_channel);
	}

	

}
