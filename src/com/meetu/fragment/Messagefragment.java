package com.meetu.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Test;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.activity.messages.LitterNoteActivity;
import com.meetu.activity.miliao.ChatGroupActivity;
import com.meetu.activity.miliao.EmojiParser;
import com.meetu.activity.miliao.XmlEmojifPullHelper;
import com.meetu.adapter.MessagesListAdapter;
import com.meetu.entity.ChatEmoji;
import com.meetu.entity.Messages;
import com.meetu.sqlite.EmojisDao;
import com.meetu.sqlite.MessagesDao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Messagefragment extends Fragment implements OnItemClickListener,OnClickListener{
	private ListView mListView;
	private List<Messages> mdataList=new ArrayList<Messages>();
	private List<Messages> mdataListCache=new ArrayList<Messages>();
	private MessagesListAdapter mAdapter;
	private View view;
	
	private MessagesDao messagesDao;
	private RelativeLayout littleNoteLayout;
	
	//表情相关 xml解析    
	private static EmojiParser parser;  
    private static List<ChatEmoji> chatEmojis; 
	private static EmojisDao emojisDao;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    view = inflater.inflate(R.layout.fragment_messages, container, false);
	    //TODO 测试	   
	    testMessages();
	    
	    loadEmoji(getActivity());
	    
	    loadData();
	    initView();
	    
	    
	    return view;
	    
	  }
	
	private static void loadEmoji(Context context) {
		 
//		try {
//			 InputStream is = context.getAssets().open("expressionImage_custom.xml");
//			  parser = new XmlEmojifPullHelper();  
////			 parser=new XmlEmojiSaxBookParser();
//				chatEmojis = parser.parse(is);
//				 for (ChatEmoji emoji : chatEmojis) {
//              //根据String类型id获取对应资源id
//		              int resID = context.getResources().getIdentifier(emoji.getFaceName(),
//								"drawable", context.getPackageName());
//		              log.e("lucifer222222","name"+emoji.getFaceName()+" resID=="+resID);		              
//		              emoji.setId(resID);
//		                
//		          }  
//		} catch (IOException e1) {
//			log.e("2",e1);
//			e1.printStackTrace();
//		} 
//		 catch (Exception e) {
//			
//			 log.e("3", e);
//			e.printStackTrace();
//		}  
		
		emojisDao=new EmojisDao(context);
         
		chatEmojis=emojisDao.getChatEmojisList();
	}

	/**
	 * 测试
	 */
	private void testMessages() {
		// TODO Auto-generated method stub
		 messagesDao=new MessagesDao(getActivity());
		 Messages messages=new Messages();
		 messages.setConversationID("1");
		 messages.setConversationType("1");//1 表示觅聊 0表示 单聊 2表示  活动群聊
		 messages.setTimeOver((new Date()).getTime());
	 
		 messages.setUnreadMsgCount(200);
		 

		 messagesDao.insert(messages);
		 
		 mdataListCache=messagesDao.getMessages();
		
	}

	private void loadData() {
		// TODO Auto-generated method stub
		mdataList=new ArrayList<Messages>();
		Messages item=new Messages();
		mdataList.add(item);
		
		Messages item2=new Messages();
		mdataList.add(item2);
		
		Messages item3=new Messages();
		mdataList.add(item3);
		
		Messages item4=new Messages();
		mdataList.add(item4);
		
	}

	private void initView() {
		mListView=(ListView) view.findViewById(R.id.listView_messages_fragment);
		mAdapter=new MessagesListAdapter(getActivity(), mdataListCache,chatEmojis);
		mListView.setAdapter(mAdapter);
		mListView.setDivider(null);
		mListView.setOnItemClickListener(this);
		
		
		littleNoteLayout=(RelativeLayout) view.findViewById(R.id.litter_notes_fragment_messages_rl);
		littleNoteLayout.setOnClickListener(this);
		
	}

	@Override
	public void setArguments(Bundle args) {
		// TODO Auto-generated method stub
		super.setArguments(args);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(getActivity(),ChatGroupActivity.class);
		startActivity(intent);
		
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		handler.sendEmptyMessage(1);
	}
	
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			
			
			switch(msg.what){
			case 1:		
				mAdapter.notifyDataSetChanged();
		
				break;
			
			}
		}

	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.litter_notes_fragment_messages_rl:
			Intent intent =new Intent(getActivity(),LitterNoteActivity.class);
			startActivity(intent);
			
			break;

		default:
			break;
		}
		
	}
	
	
	
	

}
