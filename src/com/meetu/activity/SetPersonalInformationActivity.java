package com.meetu.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;




import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.tools.BitmapCut;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.ColorDrawable;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SetPersonalInformationActivity extends Activity implements OnClickListener{
	private ImageView ivTouxiang,ivman_selector,ivwoman_selector,shangyiye,xiayiye;
	private TextView sex,birthday;
	private EditText username;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 创建状态栏的管理实例  
//		SystemBarTintManager tintManager = new SystemBarTintManager(this);  
//	    // 激活状态栏设置  
//	    tintManager.setStatusBarTintEnabled(true);  
//	    // 激活导航栏设置  
//	    tintManager.setNavigationBarTintEnabled(true);  
		//全屏
		super.getWindow();
		setContentView(R.layout.activity_shezhigerenxinxi);
		initView();
		String p=getFilesDir().toString();
		Log.d("lucifer",p);
	}

	private void initView(){
		ivTouxiang=(ImageView)super.findViewById(R.id.selfinfo1_userhead_img);
		Bitmap head=readHead();
		if(head!=null){
			ivTouxiang.setImageBitmap(head);
		}
		ivTouxiang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog();
			}
		});
		username=(EditText) super.findViewById(R.id.selfinfo1_nickname_et);
		sex=(TextView)super.findViewById(R.id.selfinfo1_sex_et);
		sex.setOnClickListener(this);
		birthday=(TextView) super.findViewById(R.id.selfinfo_birth_et);
		birthday.setOnClickListener(this);
		xiayiye=(ImageView)super.findViewById(R.id.activity_selfinfo1_to_selfinfo2_img);
		xiayiye.setOnClickListener(this);
		shangyiye=(ImageView) super.findViewById(R.id.selfinfo1_back_img);
		shangyiye.setOnClickListener(this);
		
   
      
		
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shezhigerenxinxi, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.selfinfo1_sex_et:
			initPopupWindow();
			popupWindow.showAsDropDown(v, 72, 400);	
			break;
		case R.id.iv_man_sexselector:
	
		//	ivman_selector.setImageResource(R.drawable.complete_gender_ms_720);
			popupWindow.dismiss();
			sex.setText("男生");
			break;
		case R.id.iv_woman_sexselector:
		//	ivwoman_selector.setImageResource(R.drawable.complete_gender_fs_720);
			popupWindow.dismiss();
			sex.setText("女生");
			
			break;
		case R.id.selfinfo_birth_et:
			showDialog(1);
			break;
		//下一页
		case R.id.activity_selfinfo1_to_selfinfo2_img:
			String nickname = username.getText().toString().trim();
			String xingbie = sex.getText().toString().trim();
			String birth = birthday.getText().toString().trim();
			if (!sex.equals("") && !birth.equals("") && !nickname.equals("")) {
				Intent intent=new Intent(SetPersonalInformationActivity.this,SetPersonalInformation2Activity.class);
				startActivity(intent);
			}else{
				Toast.makeText(SetPersonalInformationActivity.this, "信息填写不全",
						Toast.LENGTH_SHORT).show();
			}
			break;
		//上一页
		case R.id.selfinfo1_back_img:
			finish();
		default:
			break;
		}
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		
		Time time = new Time("GMT+8");       
        time.setToNow();      
        int year = time.year;      
        int month = time.month;      
        int day = time.monthDay;   
		// TODO Auto-generated method stub

		return new DatePickerDialog(this, new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker arg0, int year, int month, int day) {
				// TODO Auto-generated method stub
				birthday.setText(year + "-" + (month + 1) + "-" + day);
			}
		}, year, month,day);
		

	}
	
	public Bitmap readHead(){
		String file=Environment.getExternalStorageDirectory()+"/user_header.png";
		return BitmapFactory.decodeFile(file);
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
			View view = LayoutInflater.from(this).inflate(R.layout.dialog_sex_selector,
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
			ivman_selector=(ImageView)view.findViewById(R.id.iv_man_sexselector);
			ivwoman_selector=(ImageView)view.findViewById(R.id.iv_woman_sexselector);
			ivman_selector.setOnClickListener(this);
			ivwoman_selector.setOnClickListener(this);
		}
		
	}


}
