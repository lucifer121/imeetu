package com.meetu.activity.messages;

import java.util.ArrayList;
import java.util.List;

import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.R.menu;
import com.meetu.adapter.BoardPageFragmentAdapter;
import com.meetu.adapter.NotesPagerAdapter;
import com.meetu.entity.Notes;
import com.meetu.fragment.MiliaoChannelFragment;
import com.meetu.fragment.NotesChannelFragment;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Loader;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CopyOfNotesActivity extends FragmentActivity  implements OnPageChangeListener,OnClickListener{
	private ViewPager mViewPager;
	private BoardPageFragmentAdapter adapter=null;
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	private List<Notes> list=new ArrayList<Notes>();
	private NotesPagerAdapter mAdapter;
	
	//表情键盘相关
	private LinearLayout sendLinearLayout;
	private Boolean isShow=false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_notes_copy);
		
		mViewPager=(ViewPager) super.findViewById(R.id.mViewpager_notes);
		mViewPager.setOnPageChangeListener(this);
		loadData();
//		initViewPager();
		
		initView();
		
	}

	private void loadData() {
		list=new ArrayList<Notes>();
		Notes item=new Notes();
		list.add(item);
		list.add(item);
		list.add(item);
		
		
	}

	private void initViewPager() {
		// TODO 先测试5个卡片
				for(int i=0;i<5;i++){
				NotesChannelFragment frag=new NotesChannelFragment();
				Bundle bundle=new Bundle();

				frag.setArguments(bundle);
				fragmentList.add(frag);
				
				}
				adapter=new BoardPageFragmentAdapter(getSupportFragmentManager(), fragmentList);
				mViewPager.setAdapter(adapter);
				mViewPager.setOffscreenPageLimit(2);
				mViewPager.setCurrentItem(0);
				
				mViewPager.setOnClickListener(this);
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		sendLinearLayout=(LinearLayout) super.findViewById(R.id.bottom_notes_send_ll);
		mAdapter=new NotesPagerAdapter(this, list);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOffscreenPageLimit(2);
		mViewPager.setCurrentItem(0);
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mViewpager_notes:
			Toast.makeText(this, "shouye", Toast.LENGTH_SHORT).show();
			if(isShow==false){
				sendLinearLayout.setVisibility(View.VISIBLE);
				
			}else{
				sendLinearLayout.setVisibility(View.GONE);
			}
			
			
			break;

		default:
			break;
		}
		
	}

}
