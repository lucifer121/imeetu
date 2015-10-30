package com.meetu.fragment;

import java.util.ArrayList;
import java.util.List;

import com.meetu.R;
import com.avos.avoscloud.LogUtil.log;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.meetu.activity.homepage.HomePageDetialActivity;
import com.meetu.activity.homepage.JoinUsersActivity;
import com.meetu.adapter.NewsListViewAdapter;
import com.meetu.entity.Huodong;




import com.meetu.tools.DensityUtil;
import com.meetu.tools.DisplayUtils;
import com.meetu.view.ListScrollDistanceCalculator;
import com.meetu.view.ListScrollDistanceCalculator.ScrollDistanceListener;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HomePagefragment extends Fragment implements OnRefreshListener2<ListView> ,ScrollDistanceListener,OnItemClickListener,OnClickListener {
	private PullToRefreshListView lvNewsList;
	private NewsListViewAdapter adapter;
	private View view;
	private List<Huodong> data=new ArrayList<Huodong>();
	private RelativeLayout usernumber;

	private ListScrollDistanceCalculator listScrollDistanceCalculator;
	private double HightY;//item滑动的总高度
	private double HightYy;//item滑动的高度
	private double maginY;//标签移动的绝对总高度
	private double maginYy;//标签移动的距离
	private double pingHight;//屏幕的高度
	private int topHight;//屏幕上边标签栏的高度
	private int bottomHight;//屏幕下边taberbar的高度
	private int itemHight;//加载的item的高度
	private int number;//item的数量
	private int maginTop;//item 最初距离top的高度
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		pingHight=DisplayUtils.getWindowHeight(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view==null){
			 view=inflater.inflate(R.layout.fragment_homepage, null);
			 lvNewsList=(PullToRefreshListView)view.findViewById(R.id.newsList);
			 load(1,pageSize);
			 adapter=new NewsListViewAdapter(super.getActivity(),data);
			 lvNewsList.setAdapter(adapter);
			 lvNewsList.setMode(Mode.BOTH);
			 lvNewsList.setOnRefreshListener(this);
			 //lvNewsList.setRefreshing(true);	
			 listScrollDistanceCalculator = new ListScrollDistanceCalculator();
			 listScrollDistanceCalculator.setScrollDistanceListener(this);
			 lvNewsList.setOnItemClickListener(this);
			 //调用计算高度的方法
			 lvNewsList.setOnScrollListener(listScrollDistanceCalculator);
			 usernumber=(RelativeLayout) view.findViewById(R.id.usernumber_homepage_rl);
			 usernumber.setOnClickListener(this);
			// loadData(1,pageSize);
			
			
			initView();
		}
		ViewGroup parent=(ViewGroup)view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
		return view;
	}

	private void initView() {
		maginTop=DensityUtil.dip2px(getActivity(), 88);
		number=data.size();
		pingHight=DisplayUtils.getWindowHeight(getActivity());
		topHight=DensityUtil.dip2px(getActivity(), 44);
		bottomHight=DensityUtil.dip2px(getActivity(), 54);
		itemHight=DensityUtil.dip2px(getActivity(), 301)+1;//item的高度（+1是加下划线）
		
		HightY=itemHight*number-pingHight+topHight+bottomHight;//item滑动的总高度
		maginY=pingHight-topHight-bottomHight-itemHight;//标签移动的绝对总高度
		
	}

	@Override
	public void setArguments(Bundle args) {
		// TODO Auto-generated method stub
		super.setArguments(args);
	}
	public void load(int pageNo,int pageSize){
		data=new ArrayList<Huodong>();
//		data.add(new Huodong(1,R.drawable.wip_bk_dust));
		Huodong item =new Huodong();
	
		item.setImg(R.drawable.avty_cover_card1_img);
		item.setTitle("今天是个好日子");
		item.setStyle(1);
		data.add(item);
		Huodong item2 =new Huodong();
		item2.setImg(R.drawable.avty_cover_card2_img);
		item2.setTitle("心想的事儿都能成");
		item2.setStyle(2);
		data.add(item2);
		Huodong item3=new Huodong();
		item3.setImg(R.drawable.avty_cover_card3_img);
		item3.setTitle("今天是个好日子，心想的事儿都能成");
		item.setStyle(1);
		data.add(item3);
		Huodong item4 =new Huodong();
		item4.setImg(R.drawable.avty_cover_card4_img);
		item4.setTitle("今天是个好日子，心想的事儿都能成");
		item.setStyle(1);
		data.add(item4);
		Huodong item5 =new Huodong();
		item5.setImg(R.drawable.avty_cover_card5_img);
		item5.setTitle("今天是个好日子，心想的事儿都能成");
		item.setStyle(1);
		data.add(item5);

		Huodong item6 =new Huodong();
		item6.setImg(R.drawable.avty_cover_card6_img);
		item6.setTitle("今天是个好日子，心想的事儿都能成");
		item.setStyle(1);
		data.add(item6);
		Huodong item7=new Huodong();
		item7.setImg(R.drawable.avty_cover_card7_img);
		item7.setTitle("今天是个好日子，心想的事儿都能成");
		item.setStyle(1);
		data.add(item7);
		Huodong item8 =new Huodong();
		item8.setImg(R.drawable.avty_cover_card1_img);
		item8.setTitle("今天是个好日子，心想的事儿都能成");
		item.setStyle(1);
		data.add(item8);
		Huodong item9 =new Huodong();
		item9.setImg(R.drawable.avty_cover_card2_img);
		item9.setTitle("今天是个好日子，心想的事儿都能成");
		item.setStyle(1);
		data.add(item9);
		Huodong item10 =new Huodong();
		item10.setImg(R.drawable.avty_cover_card3_img);
		item10.setTitle("今天是个好日子，心想的事儿都能成");
		item.setStyle(1);
		data.add(item10);
		
		handler.sendEmptyMessage(1);
		
	}
	/**
	 * 点击进入详情页面
	 */

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		Intent intent=new Intent(getActivity(),HomePageDetialActivity.class);
		intent.putExtra("style", ""+data.get(position-1).getStyle());
		log.e("00style", "data.get(position-1).getStyle()="+data.get(position-1).getStyle()+"  data.get(position+1)= "+data.get(position-1));
		log.e("position", "position="+position);
		startActivity(intent);
//		Toast.makeText(getActivity(), ""+id, Toast.LENGTH_SHORT).show();
		
	}
	
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 1:
				adapter.notifyDataSetChanged();
				refreshComplete();
				break;
			}
		}
	
	};
	private void refreshComplete(){
		lvNewsList.postDelayed(new Runnable() {
	
	            @Override
	            public void run() {
	           	 lvNewsList.onRefreshComplete();
	            }
	    }, 500);
	}
	
	private int pageNo=1;
	private int pageSize=3;

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//		Toast.makeText(getActivity(), "下拉加载数据", Toast.LENGTH_SHORT).show();
		pageNo=1;
		load(1, pageSize);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
	//	Toast.makeText(getActivity(), "上拉加载数据", Toast.LENGTH_SHORT).show();
		pageNo++;
		load(pageNo,pageSize);
	}

	
	/**
	 * 调用计算滑动总高度的方法 total为滑动总高度
	 */
	@Override
	public void onScrollDistanceChanged(int delta, int total) {		
		RelativeLayout.LayoutParams lp=(RelativeLayout.LayoutParams)usernumber.getLayoutParams();
		int a=lp.topMargin;			
		lp.topMargin=(int) (-total*maginY/HightY)+maginTop;		
        int i=lp.topMargin;
        Log.e("e", "total "+total +" delta "+delta +" i  "+i +" HightY "+HightY+" a="+a);
        usernumber.setLayoutParams(lp);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.usernumber_homepage_rl :
//				Toast.makeText(getActivity(), "进入报名列表", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(getActivity(),JoinUsersActivity.class);
				startActivity(intent);
				
				break;

			default :
				break;
		}
		
	}
	
	 

}
