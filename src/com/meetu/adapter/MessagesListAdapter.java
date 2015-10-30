package com.meetu.adapter;

import java.util.List;


import com.meetu.R;

import com.meetu.activity.miliao.ChatGroupActivity;
import com.meetu.common.EmojisRelevantUtils;
import com.meetu.common.Spanning;
import com.meetu.entity.ChatEmoji;
import com.meetu.entity.Chatmsgs;
import com.meetu.entity.Huodong;
import com.meetu.entity.Messages;
import com.meetu.entity.User;
import com.meetu.sqlite.ChatmsgsDao;


import android.content.Context;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessagesListAdapter extends BaseAdapter {
	private Context mContext;
	private List<Messages> messagesList;
	private static List<ChatEmoji> chatEmojis; 
	private ChatmsgsDao chatmsgsDao;
	private Chatmsgs chatmsgs;
	
	
	public MessagesListAdapter(Context context,List<Messages> messagesList,List<ChatEmoji> chatEmojis){
		this.mContext=context;
		this.messagesList=messagesList;	
		chatmsgsDao=new ChatmsgsDao(context);
		
		this.chatEmojis=chatEmojis;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messagesList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return messagesList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		Messages item=messagesList.get(position);
		if(convertView==null){
			holder=new ViewHolder();
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_fragment_messages, null);
			holder.photpHead=(ImageView) convertView.findViewById(R.id.photoHead_item_fragment);
			holder.tvName=(TextView) convertView.findViewById(R.id.name_item_fragment_messages_tv);
			holder.tvContent=(TextView) convertView.findViewById(R.id.content_item_fragment_messages_tv);
			holder.tvTime=(TextView) convertView.findViewById(R.id.time_item_fragment_messages_tv);
			holder.tvNoReadMessages=(TextView) convertView.findViewById(R.id.number_noRead_item_fragment_messages_tv);
			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		
		
		//根据对话id拿到聊天对话的最后一条消息
		if(chatmsgsDao.getChatmsgsList("1").size()!=0){
			 chatmsgs=chatmsgsDao.getChatmsgsList("1").get(chatmsgsDao.getChatmsgsList("1").size()-1);
//TODO			 
			 //  如果 是 文本消息    如果有表情的话显示表情   
			 if(chatmsgs.getChatMsgType()==0||chatmsgs.getChatMsgType()==1){
				 SpannableString spannableString= EmojisRelevantUtils.getExpressionString(mContext, chatmsgs.getContent(), chatEmojis);
				 
				 holder.tvContent.setText(spannableString);
				 
//				 holder.tvContent.setText(chatmsgs.getContent());
				 
				 new Spanning().TextViewShowEmoji(mContext, chatEmojis, holder.tvContent, chatmsgs.getContent());
				 
//					holder.tvNoReadMessages.setText(item.getUnreadMsgCount());
			 }
			 if(chatmsgs.getChatMsgType()==2||chatmsgs.getChatMsgType()==3){
				 holder.tvContent.setText("[图片]");
			 }
 
			
		}else{
			holder.tvContent.setText("");
//			holder.tvNoReadMessages.setText(item.getUnreadMsgCount());
		}
		if(item.getUnreadMsgCount()>99){
			holder.tvNoReadMessages.setText("99+");
		}else{
			holder.tvNoReadMessages.setText(""+item.getUnreadMsgCount());
		}
		
		
//		holder.tvName.setText(chatmsgs.);
		
		
		
		
		return convertView;
	}
	
	private class ViewHolder{
		private TextView tvName;
		private TextView tvContent;
		private TextView tvTime;
		private TextView tvNoReadMessages;
		private ImageView photpHead;
	
	
	}
	
	


	
	

}
