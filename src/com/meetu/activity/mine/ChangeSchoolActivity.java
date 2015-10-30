package com.meetu.activity.mine;





import com.meetu.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ChangeSchoolActivity extends Activity implements OnClickListener {
	private ImageView back;
	private String school;
	private RelativeLayout backLayout,quedingLayout;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
				super.requestWindowFeature(Window.FEATURE_NO_TITLE);
				//全屏
				super.getWindow();
		setContentView(R.layout.activity_change_grade);
		school=super.getIntent().getStringExtra("school");
		initView();
	}

	private void initView() {
		back=(ImageView) super.findViewById(R.id.back_changeschool_mine);
		back.setOnClickListener(this);
		backLayout=(RelativeLayout) super.findViewById(R.id.back_changeschool_mine_rl);
		backLayout.setOnClickListener(this);
		quedingLayout=(RelativeLayout) super.findViewById(R.id.mine_changeschool_wancheng_rl);
		quedingLayout.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_grade, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_changeschool_mine_rl:
				Intent intent2=new Intent();	
				intent2.putExtra("school", school);
				ChangeSchoolActivity.this.setResult(1,intent2);
				finish();
				
				break;
			case R.id.mine_changeschool_wancheng_rl:
				break;

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
		intent.putExtra("school", school);
		ChangeSchoolActivity.this.setResult(1,intent);
		finish();
	}

}
