package com.meetu.activity.homepage;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.R.drawable;
import com.meetu.R.id;
import com.meetu.R.layout;
import com.meetu.adapter.MemoryPhotoAdapter;
import com.meetu.adapter.MinePhotoAdapter;
import com.meetu.entity.PhotoWall;
import com.meetu.tools.DensityUtil;
import com.meetu.tools.DisplayUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class MemoryPhotoActivity extends Activity implements OnPageChangeListener,OnClickListener{
	private PhotoWall dataPhotoWall;
	private String itemid;
	private ViewPager viewPager;
	int id;
	private List<PhotoWall> photolist=new ArrayList<PhotoWall>();
	private MemoryPhotoAdapter adapter;
	private ImageView favor;
	private int favourWight;//点赞布局的宽度
	private int windowWidth;
	//控件相关
	private RelativeLayout favorLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_memory_photo);
		itemid = super.getIntent().getStringExtra("id");
		id=Integer.parseInt(itemid);
		initView();
	}

	private void initView() {
		viewPager=(ViewPager) super.findViewById(R.id.viewpager_photo_memory_detial);
		load();
		adapter=new MemoryPhotoAdapter(this, photolist);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(id);
		
		favor=(ImageView) super.findViewById(R.id.favor_memory_detial);
		favor.setOnClickListener(this);
		
		favorLayout=(RelativeLayout) super.findViewById(R.id.favor_photo_memory_detial_rl);
		//按机型获取宽度，单位 像素
		favourWight=DensityUtil.dip2px(this, 80);
		windowWidth=DisplayUtils.getWindowWidth(this);
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
	public void onPageScrollStateChanged(int position) {
		// TODO Auto-generated method stub
		log.e("zcq"+"position="+position);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		if(arg2!=0){
		// TODO Auto-generated method stub
		//arg1左滑是从【0,1】，右滑【1,0】 ，arg2是滑动的像素,左滑。增大一个屏幕宽度的像素
//		log.e("lucifer"+"arg0="+arg0+"    arg1="+arg1+"    arg2="+arg2);
		
		RelativeLayout.LayoutParams params=(LayoutParams) favorLayout.getLayoutParams();
	
		params.leftMargin=(favourWight*Math.abs(arg2-windowWidth/2)/(windowWidth/2)-favourWight); 
		

		favorLayout.setLayoutParams(params);
		log.e("lucifer","favourWight="+favourWight+"  params.leftMargin ="+params.leftMargin);
		}
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		log.e("lucifer"+"position="+position);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.favor_memory_detial :
				favor.setImageResource(R.drawable.mine_photoview_fullscreen_btn_collect_hi_2x);
				
				break;

			default :
				break;
		}
		
	}


	

}
