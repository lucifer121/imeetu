package com.meetu.activity;





import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.cloud.callback.ObjFunBooleanCallback;
import com.meetu.cloud.callback.ObjFunEnumCallback;
import com.meetu.cloud.callback.ObjFunObjectCallback;
import com.meetu.cloud.wrap.ObjExecResult;
import com.meetu.cloud.wrap.ObjUserWrap;
import com.meetu.cloud.wrap.ObjWrap;

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

public class RegisterActivity extends Activity implements OnClickListener{
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
		setContentView(R.layout.activity_register);
		initView();
		
	}
	private void initView(){
		back=(ImageView) super.findViewById(R.id.activity_register_back_img);
		back.setOnClickListener(this);
		
		uphone=(EditText) super.findViewById(R.id.phone_register_et);
		upassward=(EditText) super.findViewById(R.id.password_register_et);
		uupassward=(EditText) super.findViewById(R.id.uupassword_register_et);
		uphone.addTextChangedListener(watcher);
		upassward.addTextChangedListener(watcher);
		uupassward.addTextChangedListener(watcher);
		registerButton=(Button) super.findViewById(R.id.rigister_bt_rigister);
		registerButton.setOnClickListener(this);
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zhuce, menu);
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
		case R.id.activity_register_back_img:
			finish();
		case R.id.rigister_bt_rigister:
			
			if(uphone.getText().length()!=11){
				Toast.makeText(RegisterActivity.this, "手机号码格式错误", Toast.LENGTH_SHORT).show();
			}else if (upassward.getText().length()<6) {
				Toast.makeText(RegisterActivity.this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
			}else if(!upassward.getText().toString().equals(uupassward.getText().toString())){
				Toast.makeText(RegisterActivity.this, "两次密码输入的不一致", Toast.LENGTH_SHORT).show();
			}
			else{
				String phoneString=uphone.getText().toString();
				ObjUserWrap.phoneIsAlreadyRegister(phoneString, new ObjFunEnumCallback() {
					
					@Override
					public void callback(ObjExecResult result, AVException e) {
						log.e("cunzai", "123");
						if(result==ObjExecResult.EXEC_TRUE){
							Toast.makeText(RegisterActivity.this, "此手机号 已经注册", Toast.LENGTH_SHORT).show();
						}else if(result==ObjExecResult.EXEC_FALSE){
							
							Intent intent=new Intent(RegisterActivity.this,RegisterVerificationActivity.class);
							intent.putExtra("uphone", uphone.getText().toString());
							
							intent.putExtra("upassward", upassward.getText().toString());
							startActivity(intent);
						}else{
							log.e("register", e);
						}
						
					}
				});

				
			}
			
			break;

		default:
			break;
		}
	}

}
