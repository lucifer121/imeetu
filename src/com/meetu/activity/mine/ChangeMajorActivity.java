package com.meetu.activity.mine;





import com.meetu.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChangeMajorActivity extends Activity implements OnClickListener{
	private TextView queding;
	private EditText majorEditText;
	private String major;
	private ImageView backImageView;
	private RelativeLayout backLayout,quedingLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏
		super.getWindow();
		setContentView(R.layout.activity_change_major);
		major=super.getIntent().getStringExtra("major");
		queding=(TextView) super.findViewById(R.id.mine_changemajor_majorwancheng_bt);
		queding.setOnClickListener(this);
		
		majorEditText=(EditText) findViewById(R.id.name_changmajor_et);
		majorEditText.setText(major);
		majorEditText.setOnClickListener(this);
		backImageView=(ImageView) super.findViewById(R.id.back_changemajor_mine);
		backImageView.setOnClickListener(this);
		backLayout=(RelativeLayout) super.findViewById(R.id.back_changemajor_mine_rl);
		quedingLayout=(RelativeLayout) super.findViewById(R.id.mine_changemajor_majorwancheng_rl);
		backLayout.setOnClickListener(this);
		quedingLayout.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_major, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.mine_changemajor_majorwancheng_rl:

				Intent intent=new Intent();				
				intent.putExtra("major", majorEditText.getText().toString());
				ChangeMajorActivity.this.setResult(3,intent);				
				finish();
				
				break;
			case R.id.back_changemajor_mine_rl:
				Intent intent2=new Intent();	
				intent2.putExtra("major", major);
				ChangeMajorActivity.this.setResult(3,intent2);
				finish();
				
			default :
				break;
		}
		
	}
	/**
	 * 设置点击返回键的状态
	 */
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent=new Intent();	
		intent.putExtra("major", major);
		ChangeMajorActivity.this.setResult(3,intent);
		finish();
	}
}
