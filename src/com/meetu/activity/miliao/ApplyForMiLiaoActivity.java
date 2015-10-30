package com.meetu.activity.miliao;

import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ApplyForMiLiaoActivity extends Activity implements OnClickListener{
	private RelativeLayout backLayout;
	private ImageView applyImageView;
	private EditText content;//内容
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_apply_for_mi_liao);
		initView();
	}

	private void initView() {
		backLayout=(RelativeLayout) super.findViewById(R.id.back_applyForMiLiao_rl);
		backLayout.setOnClickListener(this);
		applyImageView=(ImageView) super.findViewById(R.id.applyForMiLiao_img);
		applyImageView.setOnClickListener(this);
		content=(EditText) super.findViewById(R.id.content_applyForMiLiao_et);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_applyForMiLiao_rl:
			finish();
			
			break;
		case R.id.applyForMiLiao_img:
//			Toast.makeText(this, ""+content.getText(), Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(this,CreationChatActivity.class);
			startActivity(intent);

		default:
			break;
		}
		
	}

}
