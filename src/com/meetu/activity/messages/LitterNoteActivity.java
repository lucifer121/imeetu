package com.meetu.activity.messages;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.LogUtil.log;
import com.baidu.location.e.v;
import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.R.menu;
import com.meetu.adapter.GridRecycleLitterNoteAdapter;
import com.meetu.adapter.GridRecycleLitterNoteAdapter.OnLitterNotesItemClickCallBack;
import com.meetu.adapter.GridRecycleMiLiaoInfoAdapter;
import com.meetu.entity.LitterNotes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class LitterNoteActivity extends Activity implements OnLitterNotesItemClickCallBack,OnClickListener{
	private List<LitterNotes> mdata=new ArrayList<LitterNotes>();
	private GridRecycleLitterNoteAdapter mAdapter;
	private RecyclerView mRecyclerView;
	
	//控件相关
	private RelativeLayout backLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏
		super.getWindow();
		setContentView(R.layout.activity_litter_note);
		
		loadData();
		initView(); 
		
	}

	private void initView() {
		mRecyclerView=(RecyclerView) super.findViewById(R.id.recycleview_litter_notes);
		mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
		mAdapter=new GridRecycleLitterNoteAdapter(this,mdata);
		
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setOnItemClickLitenerBack(this);
		
		backLayout=(RelativeLayout) super.findViewById(R.id.back_litter_notes_rl);
		backLayout.setOnClickListener(this);
		
	}

	private void loadData() {
		// TODO Auto-generated method stub
		LitterNotes item=new LitterNotes();
		mdata.add(item);
		LitterNotes item2=new LitterNotes();
		mdata.add(item2);
		LitterNotes item3=new LitterNotes();
		mdata.add(item3);
		mdata.add(item3);
		mdata.add(item3);
		mdata.add(item3);
		mdata.add(item3);
		mdata.add(item3);
		
	}

	/**
	 * item  点击
	 */
	@Override
	public void onItemClick(int id) {
		log.e("lucifer", "id="+id);
		Intent intent=new Intent(this,NotesActivity.class);
		startActivity(intent);
		
	}

	@Override
	public void onItemLongClick(View view, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_litter_notes_rl:
			
			finish();
			
			break;

		default:
			break;
		}
		
	}

	

}
