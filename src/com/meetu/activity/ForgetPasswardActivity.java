package com.meetu.activity;





import com.meetu.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ForgetPasswardActivity extends Activity implements OnClickListener {
	private ImageView back;
	private Button registerButton;
	private EditText uphone,upassward,uupassward;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
				super.requestWindowFeature(Window.FEATURE_NO_TITLE);
				// 全屏
				super.getWindow();
		setContentView(R.layout.activity_forget_passward);
		initView();
	}
	private void initView(){
		back=(ImageView) super.findViewById(R.id.back_login_ForgetPassward_img);
		back.setOnClickListener(this);
		
		uphone=(EditText) super.findViewById(R.id.phone_login_ForgetPassward_et);
		upassward=(EditText) super.findViewById(R.id.passward_login_ForgetPassward_et);
		uupassward=(EditText) super.findViewById(R.id.uupassward_login_ForgetPassward_et);
		uphone.addTextChangedListener(watcher);
		upassward.addTextChangedListener(watcher);
		uupassward.addTextChangedListener(watcher);
		registerButton=(Button) super.findViewById(R.id.next_bt_forgetpassward);
		registerButton.setOnClickListener(this);
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forget_passward, menu);
		return true;
	}
	/**
	 * Editview 输入框监听事件
	 */
	
	private TextWatcher watcher = new TextWatcher() {
	    
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	        // TODO Auto-generated method stub
	    	if(uphone.getText().length()!=0&&upassward.getText().length()!=0&&uupassward.getText().length()!=0){
	    		registerButton.setBackgroundResource(R.drawable.register_login_720);
	    	}else{
	    		registerButton.setBackgroundResource(R.drawable.register_login_1_720);
	    	}
	        
	    }
	    
	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count,
	            int after) {
	        // TODO Auto-generated method stub
	        
	    }
	    
	    @Override
	    public void afterTextChanged(Editable s) {
	        // TODO Auto-generated method stub
	        
	    }
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back_login_ForgetPassward_img:
			finish();
		case R.id.next_bt_forgetpassward:
			
			if(uphone.getText().length()!=11){
				Toast.makeText(ForgetPasswardActivity.this, "手机号码格式错误", Toast.LENGTH_SHORT).show();
			}else if (upassward.getText().length()<6) {
				Toast.makeText(ForgetPasswardActivity.this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
			}else if(!upassward.getText().toString().equals(uupassward.getText().toString())){
				Toast.makeText(ForgetPasswardActivity.this, "两次密码输入的不一致", Toast.LENGTH_SHORT).show();
			}
			else{
	//			Toast.makeText(ForgetPasswardActivity.this, "可以发送数据了", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(this,ForgetPasswordVerificationActivity.class);
				intent.putExtra("uphone", uphone.getText().toString());
				intent.putExtra("upassward", upassward.getText().toString());
				startActivity(intent);
			}
			
			break;

		default:
			break;
		}
	}

}
