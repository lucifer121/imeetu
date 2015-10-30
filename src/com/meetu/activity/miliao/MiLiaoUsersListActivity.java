package com.meetu.activity.miliao;

import java.util.ArrayList;
import java.util.List;

import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.R.menu;
import com.meetu.adapter.MiLiaoUsersListAdapter;
import com.meetu.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MiLiaoUsersListActivity extends Activity implements
		OnClickListener {
	private List<User> usersList = new ArrayList<User>();
	private MiLiaoUsersListAdapter adapter;
	private ListView mlistView;

	// 控件相关
	private RelativeLayout backLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_mi_liao_users_list);
		loadData();
		mlistView = (ListView) super
				.findViewById(R.id.listview_all_miliao_userlist);
		adapter = new MiLiaoUsersListAdapter(this, usersList);
		mlistView.setAdapter(adapter);
		initView();

	}

	private void initView() {
		backLayout = (RelativeLayout) super
				.findViewById(R.id.back_miliao_UsersList_rl);
		backLayout.setOnClickListener(this);

	}

	private void loadData() {
		usersList = new ArrayList<User>();
		User item = new User();
		item.setName("刘亦菲");
		item.setSchool("北京大学");
		item.setSex("女");
		item.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		usersList.add(item);

		User item1 = new User();
		item1.setName("李连杰");
		item1.setSchool("清华大学");
		item1.setSex("男");
		item1.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		usersList.add(item1);

		User item2 = new User();
		item2.setName("赵子龙");
		item2.setSchool("北京大学");
		item2.setSex("男");
		item2.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		usersList.add(item2);

		User item3 = new User();
		item3.setName("赵子龙");
		item3.setSchool("北京大学");
		item3.setSex("男");
		item3.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		usersList.add(item3);

		User item4 = new User();
		item4.setName("赵小凤");
		item4.setSchool("北京大学");
		item4.setSex("女");
		item4.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		usersList.add(item4);

		User item5 = new User();
		item5.setName("赵子龙");
		item5.setSchool("北京大学");
		item5.setSex("男");
		item5.setHeadPhoto(R.drawable.mine_likelist_profile_default);
		usersList.add(item5);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_miliao_UsersList_rl:
			finish();

			break;

		default:
			break;
		}

	}

}
