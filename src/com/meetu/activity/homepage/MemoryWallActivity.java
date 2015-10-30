package com.meetu.activity.homepage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.adapter.StaggeredHomeAdapter;
import com.meetu.adapter.StaggeredMemoryWallAdapter;
import com.meetu.adapter.StaggeredMemoryWallAdapter.OnItemClickCallBack;
import com.meetu.common.Images;
import com.meetu.entity.PhotoWall;
import com.meetu.entity.PhotoWallTest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MemoryWallActivity extends Activity implements OnItemClickCallBack,OnClickListener{
	
	private RecyclerView mRecyclerView;
	private StaggeredMemoryWallAdapter mAdapter;
	private List<PhotoWall> data=new ArrayList<PhotoWall>();
	private RelativeLayout backLayout;
	private ImageView barrage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
				// 全屏
				super.getWindow();
		setContentView(R.layout.activity_memory_wall);
		initView();
	}
	private void initView() {
		mRecyclerView=(RecyclerView) super.findViewById(R.id.id_RecyclerView_memoryWall);
//		loaddataUrl();
		loaddata();
		mAdapter=new StaggeredMemoryWallAdapter(this, data);
		mAdapter.setOnItemClickLitener(this);
		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
		mRecyclerView.setAdapter(mAdapter);
		
		backLayout=(RelativeLayout) super.findViewById(R.id.back_memorywall_homepager_rl);
		backLayout.setOnClickListener(this);
		barrage=(ImageView) super.findViewById(R.id.barrage_memorywall_img);
		barrage.setOnClickListener(this);
	}
//	private void loaddataUrl() {
//		data=new ArrayList<PhotoWallTest>();
//		
//		PhotoWallTest item=new PhotoWallTest();
//		for(int i=0;i<Images.imageThumbUrls.length;i++){
//			
//			item.setImageURL(Images.imageThumbUrls[i].toString());
//			data.add(item);
//		}
//		
//		
//	}
	private void loaddata() {
		data=new ArrayList<PhotoWall>();
		data.add(new PhotoWall(10,R.drawable.img1_ceshi));
		data.add(new PhotoWall(2,R.drawable.img2_ceshi));
		data.add(new PhotoWall(3,R.drawable.img3_ceshi));
		data.add(new PhotoWall(4,R.drawable.img4_ceshi));
		data.add(new PhotoWall(5,R.drawable.img5_ceshi));
		data.add(new PhotoWall(5,R.drawable.img1_ceshi));
		data.add(new PhotoWall(7,R.drawable.img2_ceshi));
		data.add(new PhotoWall(10,R.drawable.img3_ceshi));
		data.add(new PhotoWall(12,R.drawable.img4_ceshi));
		data.add(new PhotoWall(10,R.drawable.img5_ceshi));
		data.add(new PhotoWall(11,R.drawable.img1_ceshi));
		data.add(new PhotoWall(12,R.drawable.img2_ceshi));
		data.add(new PhotoWall(13,R.drawable.img3_ceshi));
		data.add(new PhotoWall(14,R.drawable.img4_ceshi));
		data.add(new PhotoWall(15,R.drawable.img5_ceshi));
		
	}
	@Override
	public void onItemClick(int id) {
		Intent intent=new Intent(this,MemoryPhotoActivity.class);
		intent.putExtra("id", ""+id);
		
		startActivity(intent);
		
		this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		
	}
	@Override
	public void onItemLongClick(View view, int position) {
		
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_memorywall_homepager_rl :
				finish();
				
				
				break;
			case R.id.barrage_memorywall_img:
				Intent intent=new Intent(this,BarrageActivity.class);
				startActivity(intent);

			default :
				break;
		}
		
	}

	

}
