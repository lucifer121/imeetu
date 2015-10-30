package com.meetu.activity.mine;

import java.util.ArrayList;
import java.util.List;

import com.meetu.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.meetu.adapter.LikesListviewAdapter;
import com.meetu.adapter.NewsListViewAdapter;
import com.meetu.entity.Huodong;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FavorListActivity extends Activity implements OnRefreshListener2<ListView> ,OnItemClickListener,OnClickListener{
	private PullToRefreshListView lvNewsList;
	private LikesListviewAdapter adapter;
	private List<Huodong> data=new ArrayList<Huodong>();
	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
				super.requestWindowFeature(Window.FEATURE_NO_TITLE);
				//全屏
				super.getWindow();
		setContentView(R.layout.activity_favor_list);
		 lvNewsList=(PullToRefreshListView)super.findViewById(R.id.newlikeslist);
		 adapter=new LikesListviewAdapter(this,data);
		 lvNewsList.setAdapter(adapter);
		 lvNewsList.setMode(Mode.BOTH);
		 lvNewsList.setOnRefreshListener(this);
		 //lvNewsList.setRefreshing(true);	
		 lvNewsList.setOnItemClickListener(this);
		 initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView) findViewById(R.id.back_favorlist_mine_img);
		back.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favor_list, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_favorlist_mine_img :
				finish();
				
				break;

			default :
				break;
		}
		
	}

}
