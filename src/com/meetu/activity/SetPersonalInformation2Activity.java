package com.meetu.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;




import com.meetu.R;
import com.meetu.common.city.ArrayWheelAdapter;
import com.meetu.common.city.BaseActivity;
import com.meetu.common.city.OnWheelChangedListener;
import com.meetu.common.city.ShengshiquActivity;
import com.meetu.common.city.WheelView;
import com.meetu.tools.BitmapCut;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SetPersonalInformation2Activity extends BaseActivity implements OnClickListener,OnWheelChangedListener{
	private ImageView ivTouxiang,shangyiye,submit;
	private TextView etSchool,tvHome;
	private EditText major;
	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	private TextView mBtnConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shezhigerenxinxi2);
		initView();
	}
	private void initView(){
		ivTouxiang=(ImageView) super.findViewById(R.id.iv_touxiang_shezhigerenxinxi2);
		Bitmap head=readHead();
		if(head!=null){
			ivTouxiang.setImageBitmap(head);
		}
		ivTouxiang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog();
			}
		});
		shangyiye=(ImageView) super.findViewById(R.id.self2_back_img);
		shangyiye.setOnClickListener(this);
		etSchool=(TextView) super.findViewById(R.id.self2_school_et);
		etSchool.setOnClickListener(this);
		tvHome=(TextView) super.findViewById(R.id.self2_home_et);
		tvHome.setOnClickListener(this);
		submit=(ImageView) super.findViewById(R.id.self2_submit_img);
		submit.setOnClickListener(this);
		major=(EditText) super.findViewById(R.id.self2_major_et);
		major.setOnClickListener(this);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shezhigerenxinxi2, menu);
		return true;
	}
	public Bitmap readHead(){
		String file=Environment.getExternalStorageDirectory()+"/user_header.png";
		return BitmapFactory.decodeFile(file);
	}
	
	private void showDialog(){
		final  AlertDialog portraidlg=new AlertDialog.Builder(this).create();
		portraidlg.show();
		Window win=portraidlg.getWindow();
		win.setContentView(R.layout.dialog_show_photo);
		RadioButton portrait_native=(RadioButton)win.findViewById(R.id.Portrait_native);
		portrait_native.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1=new Intent(Intent.ACTION_PICK,null);
				intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent1, 11);
				portraidlg.dismiss();
			}
		});
		RadioButton portrait_take=(RadioButton)win.findViewById(R.id.Portrait_take);
		portrait_take.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//调用摄像头
				Intent intent2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
						"/user_header.png")));
				startActivityForResult(intent2, 22);
				portraidlg.dismiss();
			}
		});
		View viewTop=win.findViewById(R.id.view_top_dialog_sethead);
		View viewBottom=win.findViewById(R.id.view_bottom_dialog_sethead);
		//点击dialog外部，关闭dialog
				viewTop.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						portraidlg.dismiss();
					}
				});
				viewBottom.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						portraidlg.dismiss();
					}
				});
	
	}
	
	private Bitmap headerPortait;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 11:
			if(resultCode==this.RESULT_OK){
				cropPhoto(data.getData());//裁剪图片
			}
			break ;
		case 22:
			if(resultCode==this.RESULT_OK){
				File temp=new File(Environment.getExternalStorageDirectory()
						+ "/user_header.png");
				cropPhoto(Uri.fromFile(temp));//裁剪图片
				
			}
			
			break;
		case 33:
			if(data!=null){
				Bundle extras=data.getExtras();
				headerPortait=extras.getParcelable("data");
				headerPortait=BitmapCut.toRoundBitmap(headerPortait);
				if(headerPortait!=null){
					saveHeadImg(headerPortait);
					ivTouxiang.setImageBitmap(headerPortait);
				}
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public void saveHeadImg(Bitmap head){
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(new File(Environment.getExternalStorageDirectory()+"/user_header.png"));
			head.compress(CompressFormat.PNG, 100, fos);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
				try {
					if(fos!=null)fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
	
	/**
     * 调用拍照的裁剪功能
     * @param uri
     */
	
	public void cropPhoto(Uri uri){
		//调用拍照的裁剪功能
		Intent intent=new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		//aspectX aspectY 是宽和搞的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// // outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 250);
		intent.putExtra("outputY", 250);
		intent.putExtra("return-data",true);
		startActivityForResult(intent,33);
	}
	
	private PopupWindow popupWindow;
	private void initPopupWindow() {
		if (popupWindow == null) {
			View view = LayoutInflater.from(this).inflate(R.layout.shengshiqu,
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
			
			
			mViewProvince = (WheelView) view.findViewById(R.id.id_province);
			mViewCity = (WheelView) view.findViewById(R.id.id_city);
			mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
			mBtnConfirm = (TextView) view.findViewById(R.id.city_selector_shengshiqu_tv);
			
			// 添加change事件
	    	mViewProvince.addChangingListener(SetPersonalInformation2Activity.this);
	    	// 添加change事件
	    	mViewCity.addChangingListener(SetPersonalInformation2Activity.this);
	    	// 添加change事件
	    	mViewDistrict.addChangingListener(SetPersonalInformation2Activity.this);
	    	// 添加onclick事件
	    	mBtnConfirm.setOnClickListener(this);
	    	setUpData();
		}
		
	}
	
	private void setUpData() {
		initProvinceDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(SetPersonalInformation2Activity.this, mProvinceDatas));
		// 设置可见条目数量
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateCities();
		updateAreas();
	}
	
	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mViewDistrict.setCurrentItem(0);
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_touxiang_shezhigerenxinxi2:
			
			break;
		case R.id.self2_back_img:
			finish();
			break;
		case R.id.self2_school_et:
			Intent intent=new Intent(SetPersonalInformation2Activity.this,ChooseSchoolActivity.class);
			startActivity(intent);	
			break;
		case R.id.self2_home_et:
			initPopupWindow();
			popupWindow.showAsDropDown(tvHome, 0, 0);
		case R.id.self2_submit_img:
			if(etSchool.getText().length()!=0&&tvHome.getText().length()!=0&&major.getText().length()!=0){
				Toast.makeText(SetPersonalInformation2Activity.this, "可以提交传送数据了", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.city_selector_shengshiqu_tv:
			popupWindow.dismiss();
			tvHome.setText(mCurrentProviceName+mCurrentCityName);
			break;
		default:
			break;
		}
	}
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		} else if (wheel == mViewDistrict) {
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		}
	}
	

}
