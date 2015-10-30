package com.meetu.activity.homepage;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.R.menu;
import com.meetu.adapter.JoinUserAdapter;
import com.meetu.adapter.JoinUserFavorAdapter;
import com.meetu.entity.User;
import com.meetu.tools.DensityUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Loader;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class JoinUsersActivity extends Activity implements OnItemClickListener,OnClickListener{
	private ListView mlistViewAll,mListViewFavor;
	private JoinUserAdapter adapter;
	private List<User> joinUsersList=new ArrayList<User>();
	private List<User> joinUsersFavorList=new ArrayList<User>();
	private JoinUserFavorAdapter adapterfavor;
	private float itemHight=(float) 60.5;//item高度加下划线高度
	private TextView numberFavor,numberAll;
	private RelativeLayout backLayout,signLayout;
	private ImageView signImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏
		super.getWindow();
		setContentView(R.layout.activity_join_users);
		
		loadData();
		loadData2();
		mlistViewAll=(ListView) super.findViewById(R.id.listview_all_joinUsers);
		mListViewFavor=(ListView) super.findViewById(R.id.listview_favor_joinUser);
		adapter=new JoinUserAdapter(this, joinUsersList);
		
		mlistViewAll.setAdapter(adapter);
		mlistViewAll.setOnItemClickListener(this);
		adapterfavor=new JoinUserFavorAdapter(this, joinUsersFavorList);
		mListViewFavor.setAdapter(adapterfavor);
		
		
		initView();
	}

	

	private void loadData2() {
		joinUsersFavorList=new ArrayList<User>();
		User item=new User();
		item.setName("刘亦菲");
		item.setSchool("北京大学");
		item.setSex("女");
		item.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		joinUsersFavorList.add(item);
		
		User item1=new User();
		item1.setName("李连杰");
		item1.setSchool("清华大学");
		item1.setSex("男");
		item1.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		joinUsersFavorList.add(item1);
		
	}



	private void initView() {
		//动态设置listview 高度
		RelativeLayout.LayoutParams rlAll=(LayoutParams) mlistViewAll.getLayoutParams();
		rlAll.height=DensityUtil.dip2px(this,itemHight)*joinUsersList.size();
		mlistViewAll.setLayoutParams(rlAll);
		
		RelativeLayout.LayoutParams rlfavor=(LayoutParams) mListViewFavor.getLayoutParams();
		rlfavor.height=DensityUtil.dip2px(this, itemHight)*joinUsersFavorList.size()+40;
		mListViewFavor.setLayoutParams(rlfavor);
		
		log.e("lucifer", "itemHight="+itemHight);
		numberFavor=(TextView) super.findViewById(R.id.number_favor_joinUser);
		numberFavor.setText("("+joinUsersFavorList.size()+")");
		numberAll=(TextView) super.findViewById(R.id.number_all_joinUser);
		numberAll.setText("("+joinUsersList.size()+")");
		//点击事件处理
		backLayout=(RelativeLayout) super.findViewById(R.id.back_joinUsers_homepager_rl);
		backLayout.setOnClickListener(this);
		signLayout=(RelativeLayout) super.findViewById(R.id.sign_joinUsers_homepager_rl);
		signLayout.setOnClickListener(this);
		signImageView=(ImageView) super.findViewById(R.id.sign_joinUsers_homepager_img);
	}
	
	private void loadData() {
		joinUsersList=new ArrayList<User>();
		User item=new User();
		item.setName("刘亦菲");
		item.setSchool("北京大学");
		item.setSex("女");
		item.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		joinUsersList.add(item);
		
		User item1=new User();
		item1.setName("李连杰");
		item1.setSchool("清华大学");
		item1.setSex("男");
		item1.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		joinUsersList.add(item1);
		
		User item2=new User();
		item2.setName("赵子龙");
		item2.setSchool("北京大学");
		item2.setSex("男");
		item2.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		joinUsersList.add(item2);
		
		User item3=new User();
		item3.setName("赵子龙");
		item3.setSchool("北京大学");
		item3.setSex("男");
		item3.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		joinUsersList.add(item3);
		
		User item4=new User();
		item4.setName("赵子龙");
		item4.setSchool("北京大学");
		item4.setSex("男");
		item4.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		joinUsersList.add(item4);
		
		
		User item5=new User();
		item5.setName("赵子龙");
		item5.setSchool("北京大学");
		item5.setSex("男");
		item5.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		joinUsersList.add(item5);
		
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_joinUsers_homepager_rl :
				finish();
				
				break;
			case R.id.sign_joinUsers_homepager_rl:
				signImageView.setImageResource(R.drawable.acty_joinlist_img_checkedin);
				break;

			default :
				break;
		}
	}



}
