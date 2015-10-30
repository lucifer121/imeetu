package com.meetu.activity.miliao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.tools.BitmapCut;
import com.meetu.tools.DensityUtil;
import com.meetu.tools.DisplayUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;



public class CreationChatActivity extends Activity implements OnClickListener{
	private ImageView photoUpdate,uploadAgain,photo;
	private int windowHight,windowWidth;
	private EditText updateText;
	private TextView textsize;//输入的文字数量
	private RelativeLayout photoUpdateLayout,photoLayout;
	private int PhotoWidth,PhotoHight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.getWindow();
		setContentView(R.layout.activity_creation_chat);
		initView();
	}

	private void initView() {
		photoUpdateLayout=(RelativeLayout) super.findViewById(R.id.photoUpdate_creationChat_rl);
		//设置上传主题图片在屏幕的1/3高度处
		RelativeLayout.LayoutParams ll=(LayoutParams) photoUpdateLayout.getLayoutParams();
		windowHight=DisplayUtils.getWindowHeight(this);
		//状态栏加标题栏在本屏幕的高度
		int topHight=DensityUtil.dip2px(this, 64);
		//图片的半径
		int imageViewRange=DensityUtil.dip2px(this, 45);
		ll.topMargin=windowHight/3-topHight-imageViewRange;
		photoUpdateLayout.setLayoutParams(ll);
		
		//动态设置选择照片后布局的高度
		photoLayout=(RelativeLayout) super.findViewById(R.id.photo_creationChat_rl);
		RelativeLayout.LayoutParams params=(LayoutParams) photoLayout.getLayoutParams();
		//屏幕宽度
		windowWidth=DisplayUtils.getWindowWidth(this);
		PhotoWidth=windowWidth-DensityUtil.dip2px(this, 50+50);
		PhotoHight=(int) (PhotoWidth/(275.00/258.00));
		params.height=PhotoHight;
		
		photoLayout.setLayoutParams(params);
		log.e("lucifer","photoLayoutWidth="+PhotoWidth+"params.height="+params.height);
		
		photoUpdate=(ImageView) super.findViewById(R.id.photoUpdate_creationChat_img);
		
		photoUpdateLayout.setOnClickListener(this);
		
		photo=(ImageView) super.findViewById(R.id.photo_creationChat_img);
		
		uploadAgain=(ImageView) super.findViewById(R.id.uploadAgain_creationChat_img);
		uploadAgain.setOnClickListener(this);
		
		updateText=(EditText) super.findViewById(R.id.CreationChat_text_mine);
		updateText.addTextChangedListener(watcher);
		textsize=(TextView) super.findViewById(R.id.textsize_CreationChat_tv);
	}
	/**
	 * 监听editview 中输入的字符的数量 动态改变数量
	 */
	private TextWatcher watcher = new TextWatcher() {
	    
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	              
	    }
	    
	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count,
	            int after) {
	    
	    }
	    
	    @Override
	    public void afterTextChanged(Editable s) {
	    	
	       textsize.setText(""+updateText.getText().length());
	       
	    }
	};
	/**
	 * 点击事件 处理
	 */

	@SuppressLint("ShowToast")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.photoUpdate_creationChat_rl:
			
			showDialog();
			
			
			
			break;
		case R.id.uploadAgain_creationChat_img:
		//	Toast.makeText(this, "重新上传", Toast.LENGTH_SHORT).show();
			showDialog();
			break;

		default:
			break;
		}
		
	}

	private void showDialog() {
		final  AlertDialog portraidlg=new AlertDialog.Builder(this).create();
		portraidlg.show();
		Window win=portraidlg.getWindow();
		win.setContentView(R.layout.dialog_show_photo);
		RadioButton portrait_native=(RadioButton)win.findViewById(R.id.Portrait_native);
		portrait_native.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
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
				
				//调用摄像头
				Intent intent2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
						"/photo.png")));
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
						
						portraidlg.dismiss();
					}
				});
				viewBottom.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						
						portraidlg.dismiss();
					}
				});
		
	}
	
	private Bitmap headerPortait;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (requestCode) {
		case 11:
			if(resultCode==this.RESULT_OK){
				cropPhoto(data.getData());//裁剪图片
			}
			break ;
		case 22:
			if(resultCode==this.RESULT_OK){
				File temp=new File(Environment.getExternalStorageDirectory()
						+ "/photo.png");
				cropPhoto(Uri.fromFile(temp));//裁剪图片
				
			}
			
			break;
		case 33:
			if(data!=null){
				Bundle extras=data.getExtras();
				headerPortait=extras.getParcelable("data");
//				headerPortait=BitmapCut.toRoundBitmap(headerPortait);
//				headerPortait=BitmapCut.getRadiusBitmap(headerPortait);
				if(headerPortait!=null){
					saveHeadImg(headerPortait);
					photo.setImageBitmap(headerPortait);
					photoUpdateLayout.setVisibility(View.GONE);
					photoUpdateLayout.setFocusable(false);
					photoLayout.setVisibility(View.VISIBLE);
					uploadAgain.setFocusable(true);
				}
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	/**
	 * 把要上传的图片存到本地sd卡上
	 * @param photo
	 */
	public void saveHeadImg(Bitmap photo){
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(new File(Environment.getExternalStorageDirectory()+"/photo.png"));
			photo.compress(CompressFormat.PNG, 100, fos);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}finally{
			
				try {
					if(fos!=null)fos.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		
		
	}
	
	public Bitmap readHead(){
		String file=Environment.getExternalStorageDirectory()+"/photo.png";
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
		 //裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// // outputX outputY 是裁剪后图片宽高
		intent.putExtra("outputX", 275);
		intent.putExtra("outputY", 258);
		intent.putExtra("return-data",true);
		log.e("lucifer", "PhotoWidth="+PhotoWidth+"  PhotoHight"+PhotoHight);
		startActivityForResult(intent,33);
	}

	

}
