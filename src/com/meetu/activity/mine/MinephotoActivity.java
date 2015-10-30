package com.meetu.activity.mine;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalHttp;

import com.meetu.R;
import com.meetu.adapter.MinePhotoAdapter;
import com.meetu.entity.PhotoWall;



import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MinephotoActivity extends Activity implements OnClickListener ,OnPageChangeListener{
	private String itemid;
	private String pid;
	private PhotoWall dataPhotoWall;
	private ImageView photo, back, delect, favor;
	private ViewPager viewPager;
	private MinePhotoAdapter adapter;
	int id;
	private FinalHttp afinal;
	private List<PhotoWall> photolist=new ArrayList<PhotoWall>();
	private RelativeLayout backLayout,delectLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_minephoto);
		itemid = super.getIntent().getStringExtra("id");
		pid = super.getIntent().getStringExtra("id");
		id=Integer.parseInt(itemid);
	//	Toast.makeText(MinephotoActivity.this, itemid,Toast.LENGTH_SHORT).show();
		Intent intent = this.getIntent();
		dataPhotoWall = (PhotoWall) intent.getSerializableExtra("PhotoWall");
		initView();
	}
	/*
	 * 点击显示照片
	 */
	private void initView() {

//		photo = (ImageView) super.findViewById(R.id.photo_demail_mine);
//		photo.setImageResource(dataPhotoWall.getImg());
		back = (ImageView) super
				.findViewById(R.id.back_mine_photoview_fullscreen_img);
		back.setOnClickListener(this);
		delect = (ImageView) super
				.findViewById(R.id.deldect_mine_photoview_fullscreen_img);
		delect.setOnClickListener(this);
		backLayout=(RelativeLayout) super.findViewById(R.id.back_mine_photoview_fullscreen_rl);
		backLayout.setOnClickListener(this);
		delectLayout=(RelativeLayout) super.findViewById(R.id.deldect_mine_photoview_fullscreen_rl);
		delectLayout.setOnClickListener(this);
//		favor = (ImageView) super.findViewById(R.id.favor_minephoto_mine);
//		favor.setOnClickListener(this);
		viewPager=(ViewPager)super.findViewById(R.id.viewpager_photo);
		load();
		adapter=new MinePhotoAdapter(this, photolist);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(id);
		

	}

	private void load() {
		photolist=new ArrayList<PhotoWall>();
		photolist.add(new PhotoWall(1,R.drawable.img1_ceshi));
		photolist.add(new PhotoWall(2,R.drawable.img2_ceshi));
		photolist.add(new PhotoWall(3,R.drawable.img3_ceshi));
		photolist.add(new PhotoWall(4,R.drawable.img4_ceshi));
		photolist.add(new PhotoWall(5,R.drawable.img5_ceshi));
		photolist.add(new PhotoWall(1,R.drawable.img1_ceshi));
		photolist.add(new PhotoWall(2,R.drawable.img2_ceshi));
		photolist.add(new PhotoWall(3,R.drawable.img3_ceshi));
		photolist.add(new PhotoWall(4,R.drawable.img4_ceshi));
		photolist.add(new PhotoWall(5,R.drawable.img5_ceshi));
		photolist.add(new PhotoWall(1,R.drawable.img1_ceshi));
		photolist.add(new PhotoWall(2,R.drawable.img2_ceshi));
		photolist.add(new PhotoWall(3,R.drawable.img3_ceshi));
		photolist.add(new PhotoWall(4,R.drawable.img4_ceshi));
		photolist.add(new PhotoWall(5,R.drawable.img5_ceshi));
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.back_mine_photoview_fullscreen_rl :
				finish();

				break;
			case R.id.deldect_mine_photoview_fullscreen_rl :

				Toast.makeText(this, "进行删除操作", Toast.LENGTH_SHORT).show();

				break;
//			case R.id.favor_minephoto_mine :
//				Intent intent = new Intent(this, FavorListActivity.class);
//				startActivity(intent);
//				break;

			default :
				break;
		}

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

}
