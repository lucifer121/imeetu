package com.meetu.activity.homepage;

import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.R.menu;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityFeedbackActivity extends Activity implements OnClickListener{
	private TextView copy,number;
	private RelativeLayout copylLayout,backLayout,quedingLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_activity_feedback);
		initView();
	}

	private void initView() {
		copy=(TextView) super.findViewById(R.id.copy_feedback_tv);
		copy.setOnClickListener(this);
		number=(TextView) findViewById(R.id.number_feedback_tv);
		copylLayout=(RelativeLayout) super.findViewById(R.id.copy_feedback_rl);
		copylLayout.setOnClickListener(this);
		backLayout=(RelativeLayout) super.findViewById(R.id.back_feedback_homepager_rl);
		backLayout.setOnClickListener(this);
		quedingLayout=(RelativeLayout) super.findViewById(R.id.queding_feedback_homepager_rl);
		quedingLayout.setOnClickListener(this);
	}


	
	

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.copy_feedback_rl :
				//实现复制 黏贴
				ClipboardManager clip = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
				//复制
				clip.setText(number.getText());
				//黏贴.
				//clip.getText(); // 粘贴
				Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();				
				break;
			case R.id.back_feedback_homepager_rl:
				finish();
				break;
			case R.id.queding_feedback_homepager_rl:
				Toast.makeText(this, "提交数据", Toast.LENGTH_SHORT).show();
				break;

			default :
				break;
		}
		
	}
	

}
