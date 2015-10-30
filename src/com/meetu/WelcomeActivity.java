package com.meetu;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.meetu.R;
import com.meetu.activity.LoginActivity;
import com.meetu.activity.RegisterActivity;
import com.meetu.activity.RegisterVerificationActivity;
import com.meetu.activity.SetPersonalInformation2Activity;
import com.meetu.activity.SetPersonalInformationActivity;
import com.meetu.activity.homepage.ActivityFeedbackActivity;
import com.meetu.activity.homepage.BarrageActivity;
import com.meetu.activity.homepage.HomePageDetialActivity;
import com.meetu.activity.homepage.JoinActivity;
import com.meetu.activity.homepage.JoinUsersActivity;
import com.meetu.activity.homepage.MemoryWallActivity;
import com.meetu.activity.messages.CopyOfNotesActivity;
import com.meetu.activity.messages.NotesActivity;
import com.meetu.activity.miliao.ApplyForMiLiaoActivity;
import com.meetu.activity.miliao.ChatGroupActivity;
import com.meetu.activity.miliao.CreationChatActivity;
import com.meetu.activity.miliao.EmojiParser;
import com.meetu.activity.miliao.MiLiaoInfoActivity;
import com.meetu.activity.miliao.MiLiaoUsersListActivity;
import com.meetu.activity.miliao.XmlEmojifPullHelper;
import com.meetu.adapter.JoinUserAdapter;
import com.meetu.adapter.MiLiaoUsersListAdapter;
import com.meetu.baidumapdemo.BaiduMapMainActivity;
import com.meetu.common.city.ShengshiquActivity;
import com.meetu.entity.ChatEmoji;
import com.meetu.sqlite.EmojisDao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class WelcomeActivity extends Activity {
	
	/**
	 * meishi
	 */


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_welcome);
		
//		load();
		next();
		
		
		 
		  new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					
					Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
					WelcomeActivity.this.startActivity(intent);
					WelcomeActivity.this.finish();
				}
			}, 1500);
	}


private SharedPreferences sp;
private  void next() {
			
	sp=super.getSharedPreferences("app_param", Context.MODE_PRIVATE);
	
	int user=sp.getInt("user", 0);
	if(user==0){
		goIndex();
	}else{
		goHome();
	}
		}
/**
 * 首次进入app 进行的操作
 */
private void goIndex() {
	// TODO Auto-generated method stub
	Toast.makeText(this, "第一次进入", Toast.LENGTH_SHORT).show();
	
	sp=super.getSharedPreferences("app_param", Context.MODE_PRIVATE);
	sp.edit().putInt("user", 1).commit();
	//解析xml  开子线程处理任务
	 new MyAsyncTask().execute();
}

private void goHome() {
	
//	
//	Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
//	WelcomeActivity.this.startActivity(intent);
//	finish();
	
}

class MyAsyncTask extends AsyncTask<Void,Void, Void>{

	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		loadEmoji();
		return null;
	}
	 
 }
//解析 xml 返回 emoji list
private static EmojiParser parser;  
private static List<ChatEmoji> chatEmojis; 
private EmojisDao emojisDao;
private void loadEmoji() {

	//TODO 删除以前插入的数据   测试需要
//	emojisDao.deleteAll();

	try {
		emojisDao=new EmojisDao(this);
		
		 InputStream is = this.getAssets().open("expressionImage_custom.xml");
		  parser = new XmlEmojifPullHelper();  
//		 parser=new XmlEmojiSaxBookParser();
			chatEmojis = parser.parse(is);
			 for (ChatEmoji emoji : chatEmojis) {
				 //根据String类型id获取对应资源id
	              int resID = this.getResources().getIdentifier(emoji.getFaceName(),
							"drawable", this.getPackageName());
	              log.e("lucifer222222","name"+emoji.getFaceName()+" resID=="+resID);		              
	              emoji.setId(resID);     
	          } 
			 
			 //插入到sqlite 数据库
			 for(ChatEmoji emoji : chatEmojis){
				 emojisDao.insert(emoji);
			 }
			 
			 
	} catch (IOException e1) {
		log.e("2",e1); 
		e1.printStackTrace();
	} 
	 catch (Exception e) {
		
		 log.e("3", e);
		e.printStackTrace();
	}  

}




}
