package com.meetu.activity.mine;



import com.meetu.R;
import com.meetu.fragment.MinePersonalInformation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeNameActivity extends Activity implements OnClickListener{
	private TextView queding;
	private EditText nameEditText;
	private String name;
	private ImageView backImageView;
	private RelativeLayout backLayout,quedingLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
				super.requestWindowFeature(Window.FEATURE_NO_TITLE);
				//全屏
				super.getWindow();
		setContentView(R.layout.activity_change_name);
		name=super.getIntent().getStringExtra("name");
		queding=(TextView) super.findViewById(R.id.mine_changename_wancheng_bt);
		queding.setOnClickListener(this);
		
		nameEditText=(EditText) findViewById(R.id.name_changname_et);
		nameEditText.setText(name);
		nameEditText.setOnClickListener(this);
		backImageView=(ImageView) super.findViewById(R.id.back_changename_mine);
		backImageView.setOnClickListener(this);
		backLayout=(RelativeLayout) super.findViewById(R.id.back_changename_mine_rl);
		quedingLayout=(RelativeLayout) super.findViewById(R.id.mine_changename_wancheng_rl);
		backLayout.setOnClickListener(this);
		quedingLayout.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_name, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.mine_changename_wancheng_rl :

				Intent intent=new Intent();				
				intent.putExtra("name", nameEditText.getText().toString());
				ChangeNameActivity.this.setResult(0,intent);				
				finish();
				
				break;
			case R.id.back_changename_mine_rl:
				Intent intent2=new Intent();	
				intent2.putExtra("name", name);
				ChangeNameActivity.this.setResult(0,intent2);
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
		intent.putExtra("name", name);
		ChangeNameActivity.this.setResult(0,intent);
		finish();
	}

}
