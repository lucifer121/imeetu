package com.meetu.activity;

import java.util.Timer;
import java.util.TimerTask;

import net.tsz.afinal.FinalBitmap;






import com.avos.avoscloud.AVException;
import com.avos.avoscloud.LogUtil.log;
import com.baidu.location.e.r;
import com.meetu.MainActivity;
import com.meetu.R;
import com.meetu.cloud.callback.ObjFunBooleanCallback;
import com.meetu.cloud.wrap.ObjUserWrap;
import com.meetu.cloud.wrap.ObjWrap;
import com.meetu.myapplication.MyApplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony.Mms.Sent;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterVerificationActivity extends Activity implements OnClickListener{

	private TimerTask mTimerTask;
	private Timer mTimer = new Timer(true);
	private Button sent;
	private int i=59;
	private Boolean running;
	private TextView number1,number2,number3,number4,number5,number6;
	private TextView register;
	private String uphone,upassward,number;
	private TextView fasongphone;
	
	
	private LinearLayout numberLayout;
	private EditText allEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_zhuce_yanzheng);
		uphone=getIntent().getStringExtra("uphone");
		upassward=getIntent().getStringExtra("upassward");
		initView();
		
		initLoad();
//		smsCode=""+number1.getText().toString()+number2.getText().toString()+number3.getText().toString()+number4.getText().toString()+number5.getText().toString()+number6.getText().toString();
//		NewsApplication app=(NewsApplication)this.getApplicationContext();
		
	}
	/**
	 * 验证是否注册并发送短信验证码进行注册验证
	 */
	private void initLoad() {		
			ObjWrap.requestSmsCode(uphone, new ObjFunBooleanCallback() {
				
				@Override
				public void callback(boolean result, AVException e) {
					if(result==true){
						
					}else{
						log.e("failure", e);
					
					}				
				}
			});			
	}
	private void initView(){
		fasongphone=(TextView) super.findViewById(R.id.phone_fasong_zhuce);
		fasongphone.setText(uphone);
		register=(TextView) super.findViewById(R.id.activity_register_to_yanzhengma_img);
		register.setOnClickListener(this);
		sent = (Button)findViewById(R.id.register_sent_bt);
		sent.setOnClickListener(this);
		
		mTimerTask = new TimerTask(){
			public void run(){
				
					Message message = new Message();  
	                message.what = 1;  
	                doActionHandler.sendMessage(message);  
				}

			};
		mTimer.schedule(mTimerTask, 1000,1000); //在1秒后每1秒执行一次定时器中的方法，比如本文为调用log.v打印输出。
		allEditText=(EditText) super.findViewById(R.id.all_Verification_et);
		allEditText.setFocusable(true);
		allEditText.requestFocus();
		
 
	
		
		number1=(TextView) super.findViewById(R.id.one_yanzheng_wangji_et);
		number2=(TextView) super.findViewById(R.id.two_yanzheng_wangji_et);
		number3=(TextView) super.findViewById(R.id.three_yanzheng_wangji_et);
		number4=(TextView) super.findViewById(R.id.four_yanzheng_wangji_et);
		number5=(TextView) super.findViewById(R.id.five_yanzheng_wangji_et);
		number6=(TextView) super.findViewById(R.id.six_yanzheng_wangji_et);
		numberLayout=(LinearLayout) super.findViewById(R.id.number_yanzheng_ll);
		
		numberLayout.setOnClickListener(this);
		number1.setOnClickListener(this);
		number2.setOnClickListener(this);
		number3.setOnClickListener(this);
		number4.setOnClickListener(this);
		number5.setOnClickListener(this);
		number6.setOnClickListener(this);
		
		allEditText.addTextChangedListener(watcher);
//		number1.addTextChangedListener(watcher);
//		number2.addTextChangedListener(watcher);
//		number3.addTextChangedListener(watcher);
//		number4.addTextChangedListener(watcher);
//		number5.addTextChangedListener(watcher);
//		number6.addTextChangedListener(watcher);
		

	}
	

	 @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	 /**
	  * 输入框的监听事件
	  */
	 
	 private TextWatcher watcher = new TextWatcher() {
		    
		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        // TODO Auto-generated method stub

		    	if(allEditText.length()>0){
		    		if(allEditText.length()==1){
		    			number1.setBackgroundResource(R.drawable.register_ver_s_720);
		    			String num2=(allEditText.getText()).toString().substring(0);
		    			log.e("lucifer", "num1=="+num2+"  aaa"+allEditText.getText());
		    			number1.setText((allEditText.getText()).toString().substring(0));
		    			
		    		}else if(allEditText.length()==2){
		    			number2.setBackgroundResource(R.drawable.register_ver_s_720);
		    			String num2=(allEditText.getText()).toString().substring(1);
		    			log.e("lucifer", "num2=="+num2+"  aaa"+allEditText.getText());
		    			number2.setText((allEditText.getText()).toString().substring(1));
		    		}else if(allEditText.length()==3){
		    			number3.setBackgroundResource(R.drawable.register_ver_s_720);
		    			String num3=(allEditText.getText()).toString().substring(2);
		    			log.e("lucifer", "num2=="+num3+"  aaa"+allEditText.getText());
		    			number3.setText((allEditText.getText()).toString().substring(2));
		    		}else if(allEditText.length()==4){
		    			number4.setBackgroundResource(R.drawable.register_ver_s_720);
		    			String num4=(allEditText.getText()).toString().substring(3);
		    			log.e("lucifer", "num4=="+num4+"  aaa"+allEditText.getText());
		    			number4.setText((allEditText.getText()).toString().substring(3));
		    		}else if(allEditText.length()==5){
		    			number5.setBackgroundResource(R.drawable.register_ver_s_720);
		    			String num2=(allEditText.getText()).toString().substring(4);
		    			log.e("lucifer", "num5=="+num2+"  aaa"+allEditText.getText());
		    			number5.setText((allEditText.getText()).toString().substring(4));
		    			
		    		}else if(allEditText.length()==6){
		    			number6.setBackgroundResource(R.drawable.register_ver_s_720);
		    			String num2=(allEditText.getText()).toString().substring(5);
		    			log.e("lucifer", "num6=="+num2+"  aaa"+allEditText.getText());
		    			number6.setText((allEditText.getText()).toString().substring(5));	
		    		}
		    		
		    		if(allEditText.length()<6){
		    			number6.setText("");
		    			number6.setBackgroundResource(R.drawable.register_ver_h_720);
		    		} 
		    		if(allEditText.length()<5){
		    			number5.setText("");
		    			number5.setBackgroundResource(R.drawable.register_ver_h_720);
		    		}
		    		if(allEditText.length()<4){
		    			number4.setText("");
		    			number4.setBackgroundResource(R.drawable.register_ver_h_720);
		    			
		    		}
		    		if(allEditText.length()<3){
		    			number3.setText("");
		    			number3.setBackgroundResource(R.drawable.register_ver_h_720);
		    		}
		    		if(allEditText.length()<2){
		    			number2.setText("");
		    			number2.setBackgroundResource(R.drawable.register_ver_h_720);
		    		}
		    		if(allEditText.length()<1){
		    			number1.setText("");
		    			number1.setBackgroundResource(R.drawable.register_ver_h_720);
		    		}
		
		    	}
		    }
		    
		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		            int after) {
		        // TODO Auto-generated method stub
	
		    }
		    
		    @Override
		    public void afterTextChanged(Editable s) {
		    	log.e("lucifer", " changdu=="+allEditText.length());
		    	if(allEditText.length()==0){
	    			number1.setText("");
	    			number1.setBackgroundResource(R.drawable.register_ver_h_720);
	    		}
		        
		    }
		};


	/** 
     * do some action 
     */  
    private Handler doActionHandler = new Handler() {
    	

    	    @Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg);  
            int msgId = msg.what;  
            switch (msgId) {  
                case 1:  
                    // do some action
                	
                	sent.setEnabled(false);
                	sent.setText("已发送验证码"+i);
    				i--;
    				if(i<0){
    					sent.setText("重新发送");
    					sent.setEnabled(true);
    					sent.setBackgroundResource(R.drawable.register_sent_light_720);
    					
    					
    					
    					break;
    				}
                    break;  
                default:  
                    break;  
            }  
        }  
    }; 
    /**
     * 
     */
    public class StopThread extends  Thread {

        private boolean  _run  = true;
        public void stopThread( boolean  run) {
             this ._run = !run;
        }
    }
 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.register_sent_bt:
				i=9;
				Toast.makeText(RegisterVerificationActivity.this, "可点击测试", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.activity_register_to_yanzhengma_img:
			
			number=allEditText.getText().toString();
			log.e("yanzhengma", number.toString());
			
//			Toast.makeText(RegisterVerificationActivity.this, number, Toast.LENGTH_SHORT).show();
			 ObjUserWrap.register(uphone, upassward, number, new ObjFunBooleanCallback() {
				
				@Override
				public void callback(boolean result, AVException e) {
					if(result==true){
//						Toast.makeText(RegisterVerificationActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
						Intent intent=new Intent(RegisterVerificationActivity.this,MainActivity.class);
						startActivity(intent);
					}else{
						log.e("failure", e);
					}
					
				}
			});
			
			break;
		case R.id.number_yanzheng_ll:
		case R.id.one_yanzheng_wangji_et:
		case R.id.two_yanzheng_wangji_et:
		case R.id.three_yanzheng_wangji_et:
		case R.id.four_yanzheng_wangji_et:
		case R.id.five_yanzheng_wangji_et:
		case R.id.six_yanzheng_wangji_et:
			allEditText.setFocusable(true);
			allEditText.requestFocus();
			InputMethodManager inputManager = (InputMethodManager)allEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);    
			inputManager.showSoftInput(allEditText, 0); 
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			allEditText.requestFocusFromTouch();
			log.e("lucifer","dianji");
		break;
			
		default:
			break;
		}
	}

}
