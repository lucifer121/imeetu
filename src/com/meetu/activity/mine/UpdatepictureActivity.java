package com.meetu.activity.mine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.meetu.R;
import com.meetu.entity.Middle;

import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class UpdatepictureActivity extends Activity implements OnClickListener{
	private EditText updateText;
	private TextView textsize,quxiao,wancheng;
	private ImageView picture;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏
		super.getWindow();
		setContentView(R.layout.activity_updatepicture);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		updateText=(EditText) super.findViewById(R.id.update_text_mine);
		updateText.addTextChangedListener(watcher);
		textsize=(TextView) super.findViewById(R.id.textsize_update_mine);
		picture=(ImageView) super.findViewById(R.id.update_picture_mine);
		quxiao=(TextView) super.findViewById(R.id.back_update_mine_tv);
		wancheng=(TextView) super.findViewById(R.id.wancheng_update_mine_tv);
		quxiao.setOnClickListener(this);
		wancheng.setOnClickListener(this);
		update();
		
		
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
	
	public void update(){
		ContentResolver resolver = getContentResolver();
//		headerPortait=BitmapCut.toRoundBitmap(headerPortait);
		if(Middle.bimaBitmap!=null){
//			saveHeadImg(bitmap);
			picture.setImageBitmap(Middle.bimaBitmap);
		}
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
		getMenuInflater().inflate(R.menu.updatepicture, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_update_mine_tv :
				finish();				
				break;
			case R.id.wancheng_update_mine_tv:
				Toast.makeText(this, "进行上传", Toast.LENGTH_SHORT).show();
				break;

			default :
				break;
		}
		
	}

}
