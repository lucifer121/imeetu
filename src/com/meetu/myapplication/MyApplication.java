package com.meetu.myapplication;

import java.util.Set;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import net.tsz.afinal.FinalBitmap;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

	private FinalBitmap finalBitmap = null;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		finalBitmap = FinalBitmap.create(this);
		finalBitmap.configBitmapLoadThreadSize(3);// 线程尺寸
		finalBitmap.configDiskCachePath(getFilesDir().toString());//
		finalBitmap.configDiskCacheSize(1024 * 1024 * 10);
		int memory = (int) Runtime.getRuntime().maxMemory() / 8;
		finalBitmap.configMemoryCacheSize(memory);
		
		log.e("AVOSCloud", "3254");
		AVOSCloud.initialize(this,
				"tcd4rj3s3c54bdlkv1vfu5puvu9c2k96ur9kge3qvptqxp8p",
				"8fpp7j815746jg9x26f0d3c5p76xqkyqm586v2onvx3m2k7a");
		AVOSCloud.setDebugLogEnabled(true);
		// 注册默认的消息处理逻辑
		AVIMMessageManager
				.registerDefaultMessageHandler(new CustomMessageHandler());

		// finalBitmap.configLoadingImage(R.drawable.default_news_img);
		// finalBitmap.configLoadfailImage(R.drawable.default_news_img);
		// finalBitmap.configBitmapMaxHeight(bitmapHeight);

		// JPushInterface.init(this);
		// JPushInterface.setDebugMode(true);
		// JPushInterface.setAlias(this, "123", new TagAliasCallback() {
		//
		// @Override
		// public void gotResult(int arg0, String arg1, Set<String> arg2) {
		// // TODO Auto-generated method stub
		// if(arg0!=0){
		//
		// }
		// }
		// });
	}

	public FinalBitmap getFinalBitmap() {
		return finalBitmap;
	}

	public static class CustomMessageHandler extends AVIMMessageHandler {
		// 接收到消息后的处理逻辑
		@Override
		public void onMessage(AVIMMessage message,
				AVIMConversation conversation, AVIMClient client) {
			if (message instanceof AVIMTextMessage) {
				Log.d("Tom & Jerry", ((AVIMTextMessage) message).getText());
			}
		}

		public void onMessageReceipt(AVIMMessage message,
				AVIMConversation conversation, AVIMClient client) {

		}
	}

	public void jerryReceiveMsgFromTom() {
		// Jerry登录
		AVIMClient jerry = AVIMClient.getInstance("Jerry");
		jerry.open(new AVIMClientCallback() {

			@Override
			public void done(AVIMClient client, AVIMException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					// ...//登录成功后的逻辑
				}
			}
		});
	}

}
