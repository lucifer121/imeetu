package com.meetu.common;

import java.util.List;


import com.avos.avoscloud.LogUtil.log;
import com.meetu.entity.ChatEmoji;

import android.content.Context;

import android.os.AsyncTask;
import android.text.SpannableString;

import android.widget.TextView;

public class Spanning {
	private static Context mContext;
	private static TextView mTextView;
	private static List<ChatEmoji> mchatEmojis;
	
	public  void TextViewShowEmoji(Context context, List<ChatEmoji> chatEmojis,TextView textView,String content){
		mContext=context;
		
		mchatEmojis=chatEmojis;
		
		new MyAsyncTask(textView,content).equals(content);
		
	}
	
	private class MyAsyncTask extends AsyncTask<String, Void, SpannableString>{
		private TextView mTextView;
		private String content;
		public MyAsyncTask(TextView textView,String contString){
			mTextView=textView;
			content=contString;
			
		}

		@Override
		protected SpannableString doInBackground(String... params) {
			// TODO Auto-generated method stub
			SpannableString spannableString= EmojisRelevantUtils.getExpressionString(mContext, content, mchatEmojis);
			log.e("lucifer", content+"  spannableString="+spannableString);
			return spannableString;
		}

		@Override
		protected void onPostExecute(SpannableString spannableString) {
			// TODO Auto-generated method stub
			
			super.onPostExecute(spannableString);
			mTextView.setText(spannableString);
		}
		
	}

}
