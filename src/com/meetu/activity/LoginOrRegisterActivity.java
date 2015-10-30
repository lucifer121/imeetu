package com.meetu.activity;





import com.meetu.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginOrRegisterActivity extends Activity implements OnClickListener{
	private Button denglu,zhuce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_or_register);
		initView();
	}
	private void initView(){
		denglu=(Button)super.findViewById(R.id.bt_denglu_dengluorzhuce);
		zhuce=(Button)super.findViewById(R.id.bt_zhuce_dengluorzhuce);
		denglu.setOnClickListener(this);
		zhuce.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.denglu_or_zhuce, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_denglu_dengluorzhuce:
			Intent intent1=new Intent(LoginOrRegisterActivity.this,LoginActivity.class);
			startActivity(intent1);
			
			break;
		case R.id.bt_zhuce_dengluorzhuce:
			Intent intent2=new Intent(LoginOrRegisterActivity.this,RegisterActivity.class);
			startActivity(intent2);
			break;

		default:
			break;
		}
		
	}

}
