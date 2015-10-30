package com.meetu.fragment;




import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.activity.miliao.ApplyForMiLiaoActivity;
import com.meetu.activity.miliao.ChatGroupActivity;
import com.meetu.adapter.BoardPageFragmentAdapter;
import com.meetu.cloud.wrap.ObjChatMessage;
import com.meetu.tools.DepthPageTransformer;
import com.meetu.tools.MyZoomOutPageTransformer;
import com.meetu.tools.ZoomOutPageTransformer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Miliaofragment extends Fragment implements OnPageChangeListener,OnClickListener{
	//viewpager相关
	private View view;
	private ViewPager viewPager;
	private BoardPageFragmentAdapter adapter=null;
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	
	//控件相关
	private TextView numberAll,numberPosition;
	private RelativeLayout addLayout,joinLayout;
	
	

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view==null){
			view=inflater.inflate(R.layout.fragment_miliao, null);
			viewPager=(ViewPager)view.findViewById(R.id.vpNewsList_miliao);	
			//设置viewpage的切换动画
			viewPager.setPageTransformer(true, new MyZoomOutPageTransformer());
			
			viewPager.setOnPageChangeListener(this);
			
			numberAll=(TextView) view.findViewById(R.id.numberAll_miliao);	
			numberPosition=(TextView) view.findViewById(R.id.numberPosition_miliao);
			addLayout=(RelativeLayout) view.findViewById(R.id.add_miliao_rl);
			joinLayout=(RelativeLayout) view.findViewById(R.id.join_miliao_rl);
			addLayout.setOnClickListener(this);
			joinLayout.setOnClickListener(this);
			
			initViewPager();
			initView();
		}
		ViewGroup parent=(ViewGroup) view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
		return view;
	}

/**
 * 控件相关处理
 */
	private void initView() {
		
		
		addLayout.setOnClickListener(this);
		joinLayout.setOnClickListener(this);
		//TODO 此处应设置为卡片的总数量。
		
		numberAll.setText("5");
		numberPosition.setText(""+1);
	}
/**
 * viewpager 相关处理
 */

	private void initViewPager() {
		// TODO 先测试5个卡片
		for(int i=0;i<5;i++){
		MiliaoChannelFragment frag=new MiliaoChannelFragment();
		Bundle bundle=new Bundle();
//		bundle.putString("weburl", channelList.get(i).getWeburl());
//		bundle.putString("id", channelList.get(i).getId());
//		bundle.putString("name", channelList.get(i).getName());
//		bundle.putString("hweburl", channelList.get(i).getHweburl());
		frag.setArguments(bundle);
		fragmentList.add(frag);
		}
		adapter=new BoardPageFragmentAdapter(super.getActivity().getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(adapter);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setCurrentItem(0);
		 
		
	}


	@Override
	public void onPageScrollStateChanged(int position) {
		// TODO Auto-generated method stub
//		log.e("lucifer", "position="+position);		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		log.e("lucifer", "arg0="+arg0 +" arg1="+arg1+ "arg2="+arg2);
	}


	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		log.e("lucifer", "position="+position);
		numberPosition.setText(""+(position+1));
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//申请觅聊
		case R.id.add_miliao_rl:
			Intent intent=new Intent(getActivity(),ApplyForMiLiaoActivity.class);
			startActivity(intent);
			
			break;
		//加入觅聊
		case R.id.join_miliao_rl:
			//TODO 使用测试 clientId  ConversationId  进行对话
//			
			Intent intent2=new Intent(getActivity(),ChatGroupActivity.class);
			startActivity(intent2);
//			ObjChatMessage.JoinConversation(getActivity(), "55d050e600b0de09f8a0ab89", "560291e260b2b52ca74556a1");
			break;

		default:
			break;
		}
		
	}

}
