package com.meetu.activity.mine;

import com.meetu.R;


import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChangeBirthdayActivity extends Activity implements OnClickListener{
	private TextView birthday;
	private TextView queding;
	private String birth;
	private ImageView backImageView;
	private RelativeLayout backlLayout,quedingLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏
		super.getWindow();
		setContentView(R.layout.activity_change_birthday);
		birth=super.getIntent().getStringExtra("birthday");
		intiView();
	}

	private void intiView() {
		birthday=(TextView) super.findViewById(R.id.name_changbirth_et);
		birthday.setText(birth);
		birthday.setOnClickListener(this);
		queding=(TextView) super.findViewById(R.id.mine_changebirth_wancheng_bt);
//		queding.setOnClickListener(this);
		backImageView=(ImageView) super.findViewById(R.id.back_changebirth_mine);
//		backImageView.setOnClickListener(this);
		backlLayout=(RelativeLayout) super.findViewById(R.id.back_changebirth_mine_rl);
		backlLayout.setOnClickListener(this);
		quedingLayout=(RelativeLayout) super.findViewById(R.id.mine_changebirth_wancheng_rl);
		quedingLayout.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_birthday, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.name_changbirth_et :
				this.showDialog(1);
				new DatePickerDialog(this, new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker arg0, int year, int month, int day) {
						// TODO Auto-generated method stub
						birthday.setText(year + "-" + (month + 1) + "-" + day);
					}
				}, 2015, 7, 13).show();
				break;
							
			case R.id.mine_changebirth_wancheng_rl:
				Intent intent=new Intent();				
				intent.putExtra("birthday", birthday.getText().toString());
				ChangeBirthdayActivity.this.setResult(5,intent);				
				finish();
				break;
			case R.id.back_changebirth_mine_rl:
				Intent intent2=new Intent();	
				intent2.putExtra("birthday", birth);
				ChangeBirthdayActivity.this.setResult(5,intent2);
				finish();
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
		intent.putExtra("birthday", birth);
		ChangeBirthdayActivity.this.setResult(5,intent);
		finish();
	}

}
