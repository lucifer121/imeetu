package com.meetu;

import java.util.ArrayList;



import com.meetu.R;
import com.meetu.adapter.BoardPageFragmentAdapter;
import com.meetu.db.TabDb;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends FragmentActivity implements OnTabChangeListener{
	/**
	 * 111
	 */
	
	private FragmentTabHost tabHost;
	//private ContentViewPager vpContent;
	private FrameLayout contentLayout;
	private ArrayList<Fragment> fragList=null;
	private BoardPageFragmentAdapter adapter;
	private String pageString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.getWindow();
		setContentView(R.layout.activity_main);
		
		tabHost=(FragmentTabHost)super.findViewById(android.R.id.tabhost);
		contentLayout=(FrameLayout)super.findViewById(R.id.contentLayout);
		tabHost.setup(this,super.getSupportFragmentManager(),R.id.contentLayout);
		tabHost.getTabWidget().setDividerDrawable(null);
		tabHost.setOnTabChangedListener(this);
		initTab();	
		pageString=super.getIntent().getStringExtra("page");
		
		
	}
	
	private void initTab(){
		String tabs[]=TabDb.getTabsTxt();
		for(int i=0;i<tabs.length;i++){
			TabSpec tabSpec=tabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
			tabHost.addTab(tabSpec,TabDb.getFragments()[i],null);
			tabHost.setTag(i);		
		}
	}
	private View getTabView(int idx){
		View view=LayoutInflater.from(this).inflate(R.layout.footer_tabs,null);
		((TextView)view.findViewById(R.id.tvTab)).setText(TabDb.getTabsTxt()[idx]);
		if(idx==0){
			((TextView)view.findViewById(R.id.tvTab)).setTextColor(this.getResources().getColor(R.color.tablebar_check));
			((ImageView)view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImgLight()[idx]);
		}else{
			((ImageView)view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImg()[idx]);
		}
		return view;
	}

	

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		updateTab();
	}
	
	private void updateTab(){
		TabWidget tabw=tabHost.getTabWidget();
		for(int i=0;i<tabw.getChildCount();i++){
			View view=tabw.getChildAt(i);
			ImageView iv=(ImageView)view.findViewById(R.id.ivImg);
			if(i==tabHost.getCurrentTab()){
				((TextView)view.findViewById(R.id.tvTab)).setTextColor(this.getResources().getColor(R.color.tablebar_check));
				iv.setImageResource(TabDb.getTabsImgLight()[i]);
			}else{
				((TextView)view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.foot_txt_gray));
				iv.setImageResource(TabDb.getTabsImg()[i]);
			}
			
		}
	}
	/**
	 * 按两次退出
	 */
	private long endTime=0;
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(System.currentTimeMillis()-endTime>3000){
			Toast.makeText(this, "再按一次返回键退出程序",Toast.LENGTH_LONG).show();
			endTime=System.currentTimeMillis();
		}else{
			System.exit(0);
		}
	}

}
