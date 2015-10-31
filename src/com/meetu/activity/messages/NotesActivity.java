package com.meetu.activity.messages;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.R.menu;
import com.meetu.activity.miliao.ChatGroupActivity;
import com.meetu.adapter.BoardPageFragmentAdapter;
import com.meetu.fragment.MiliaoChannelFragment;
import com.meetu.fragment.NotesChannelFragment;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Loader;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class NotesActivity extends FragmentActivity  implements OnPageChangeListener,OnClickListener{
	private ViewPager mViewPager;
	private BoardPageFragmentAdapter adapter=null;
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	
	//控件相关
	private RelativeLayout backLayout;
	private static RelativeLayout bottomLayout;
	private RelativeLayout disposeLayout;
	private RelativeLayout laheilayout,jubaolayout;
	
	//表情键盘相关
	private LinearLayout sendLinearLayout;
	private Boolean isShow=false;
	
	private int beforeID=0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_notes);
		
		mViewPager=(ViewPager) super.findViewById(R.id.mViewpager_notes);
		mViewPager.setOnPageChangeListener(this);
		initViewPager();
		initView();
		
	}

	private void initViewPager() {
		// TODO 先测试5个卡片
				for(int i=0;i<2;i++){
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
		backLayout=(RelativeLayout) super.findViewById(R.id.back_notes_top_rl);
		backLayout.setOnClickListener(this);
		
		bottomLayout=(RelativeLayout) super.findViewById(R.id.send_bottom_rl);
		disposeLayout=(RelativeLayout) super.findViewById(R.id.manage_notes_center_rl);
		disposeLayout.setOnClickListener(this);
		
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
		visible();
		log.d("lucifer", ""+arg0);
//		NotesChannelFragment.dismissAll();
		
		//获取系统软件盘的状态
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
				boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开  
				log.e("lucifer", "isOpen=="+isOpen);
	
				hideKeyBoard();
				
				NotesChannelFragment.mEditText.clearFocus();
				
				//暴力 拿到 fragment
				((NotesChannelFragment)fragmentList.get(beforeID)).isShowEditLayout();
				
				beforeID=arg0;
				
				
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mViewpager_notes:
			Toast.makeText(this, "shouye", Toast.LENGTH_SHORT).show();
//			if(isShow==false){
//				sendLinearLayout.setVisibility(View.VISIBLE);
//				
//			}else{
//				sendLinearLayout.setVisibility(View.GONE);
//			}
		
			break;
		case R.id.back_notes_top_rl:
			finish();
			break;
		case R.id.manage_notes_center_rl:
			showPopWindow();
			popupWindow.showAsDropDown(v, 0, 0);
			
			break;

		default:
			break;
		}
		
	}
	
	private PopupWindow popupWindow;
	private void showPopWindow() {
		if (popupWindow == null) {
			View view = LayoutInflater.from(this).inflate(R.layout.item_laheijubao,
					null);		
			popupWindow = new PopupWindow(view,
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT);
			// 设置外观
			popupWindow.setFocusable(true);
			popupWindow.setOutsideTouchable(true);
			ColorDrawable colorDrawable = new ColorDrawable();
			popupWindow.setBackgroundDrawable(colorDrawable);
		//	tvTitle=(TextView)view.findViewById(R.id.tvcolectList);
			laheilayout=(RelativeLayout) view.findViewById(R.id.lahei_item_laheijubao_rl);
			jubaolayout=(RelativeLayout) view.findViewById(R.id.jubao_item_laheijubao_rl);
			laheilayout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.e("lucifer", "拉黑");
					popupWindow.dismiss();
				}
			});
			jubaolayout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.e("lucifer", "举报");
					popupWindow.dismiss();
				}
			});
		}
		
	}

	/**
	 * 控制 底部布局 显示和隐藏
	 */
	public static  void dismiss(){
		
		bottomLayout.setVisibility(View.GONE);
		
	}
	/**
	 * 控制 底部布局 显示
	 */
	public static void visible(){
		bottomLayout.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return super.dispatchKeyEvent(event);
	}
	
	protected void hideKeyBoard(){
		View  curFocusView=getCurrentFocus();
		if(curFocusView!=null){
			((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
			.hideSoftInputFromWindow(curFocusView.getWindowToken(), 0);
		}
	}
	
	

}
