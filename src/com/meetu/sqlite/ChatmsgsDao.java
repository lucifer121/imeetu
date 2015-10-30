package com.meetu.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.meetu.entity.Chatmsgs;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ChatmsgsDao {
	
	private  MySqliteDBHelper helper;
	
	public ChatmsgsDao(Context context){
		helper=new MySqliteDBHelper(context);
	}
	
	/**
	 * 插入数据数据
	 * @param chatmsgs 消息实体
	 */
	public void insert(Chatmsgs chatmsgs){
		SQLiteDatabase db=helper.getReadableDatabase();
		db.execSQL("insert into chatmsgs values(null," +
				"?,?,?,?,?," +
				"?,?,?,?,?," +
				"?,?,?,?,?," +
				"?,?,?)",
				new Object[]{chatmsgs.getMessageId(),chatmsgs.getClientId(),chatmsgs.getConversationId(),chatmsgs.getChatMsgType(),chatmsgs.getChatMsgDirection(),
				chatmsgs.getChatMsgStatus(),chatmsgs.getIsShowTime(),chatmsgs.getSendTimeStamp(),chatmsgs.getDeliveredTimeStamp(),chatmsgs.getContent(),
				chatmsgs.getImgMsgImageUrl(),chatmsgs.getImgMsgImageWidth(),chatmsgs.getImgMsgImageHeight(),chatmsgs.getNowJoinUserId(),chatmsgs.getNotificationBaseContent(),
				chatmsgs.getNotificationActyContent(),chatmsgs.getNotificationActyTitle(),chatmsgs.getNotificationActyTitleSub()});
		db.close();
		
	}
	/**
	 * 修改更新消息数据
	 * @param chatmsgs 消息实体
	 */
	public void update(Chatmsgs chatmsgs){
		SQLiteDatabase db=helper.getReadableDatabase();
		String sql="update chatmsgs set _message_id=?,_client_id=?,_conversation_id=?,_chat_msg_type=?,_chat_msg_direction=?" +
				"_chat_msg_status=?,_is_show_time=?,_send_time_stamp=?,_delivered_time_stamp=?,_content=?" +
				"_img_msg_image_url=?,_img_msg_image_width=?,_img_msg_image_height=?,_now_join_user_id=?,_notification_base_content" +
				"_notification_acty_content=?,_notification_acty_title=?,_notification_acty_title_sub=? where _message_cache_id=?";
		db.execSQL(sql,new Object[]{chatmsgs.getMessageId(),chatmsgs.getClientId(),chatmsgs.getConversationId(),chatmsgs.getChatMsgType(),chatmsgs.getChatMsgDirection(),
				chatmsgs.getChatMsgStatus(),chatmsgs.getIsShowTime(),chatmsgs.getSendTimeStamp(),chatmsgs.getDeliveredTimeStamp(),chatmsgs.getContent(),
				chatmsgs.getImgMsgImageUrl(),chatmsgs.getImgMsgImageWidth(),chatmsgs.getImgMsgImageHeight(),chatmsgs.getNowJoinUserId(),chatmsgs.getNotificationBaseContent(),
				chatmsgs.getNotificationActyContent(),chatmsgs.getNotificationActyTitle(),chatmsgs.getNotificationActyTitleSub(),chatmsgs.getMessageCacheId()});
		db.close();
		
	}
	/**
	 * 删除操作
	 * @param messageCacheId 消息的缓存id
	 */
	public void delete(String messageCacheId){
		SQLiteDatabase db=helper.getReadableDatabase();
		String sql="delete from chatmsgs where _message_cache_id=?";
		db.execSQL(sql,new Object[]{messageCacheId});
		db.close();
		
	}
	/**
	 * 
	 * 删除所有的消息信息缓存
	 */
	public void deleteAll(){
		SQLiteDatabase db=helper.getReadableDatabase();
		String sql="delete from chatmsgs ";
		db.execSQL(sql);
		db.close();
		
	}
	/**
	 * 删除单个对话缓存消息
	 * @param conversationId 对话id
	 */
	public void deleteConversationId(String conversationId){
		SQLiteDatabase db=helper.getReadableDatabase();
		String sql="delete from chatmsgs where _conversation_id=?";
		db.execSQL(sql,new Object[]{conversationId});
		db.close();
		
	}
	/**
	 * 查询消息列表
	 * @param conversationId 属于哪个对话的id
	 * @return
	 */
	public List<Chatmsgs> getChatmsgsList(String conversationId){
		SQLiteDatabase db=helper.getReadableDatabase();
		Cursor c=db.rawQuery("select * from chatmsgs " +
				"where _conversation_id=?", new String[]{conversationId});
		List<Chatmsgs> list=new ArrayList<Chatmsgs>();
		while(c.moveToNext()){
			Chatmsgs chatmsgs=new Chatmsgs();
			
			chatmsgs.setMessageCacheId(c.getString(c.getColumnIndex("_message_cache_id")));
			chatmsgs.setMessageId(c.getString(c.getColumnIndex("_message_id")));
			chatmsgs.setClientId(c.getString(c.getColumnIndex("_client_id")));
			chatmsgs.setConversationId(c.getString(c.getColumnIndex("_conversation_id")));
			chatmsgs.setChatMsgType(c.getInt(c.getColumnIndex("_chat_msg_type")));
			chatmsgs.setChatMsgDirection(c.getInt(c.getColumnIndex("_chat_msg_direction")));
			chatmsgs.setChatMsgStatus(c.getInt(c.getColumnIndex("_chat_msg_status")));
			chatmsgs.setIsShowTime(c.getInt(c.getColumnIndex("_is_show_time")));
			chatmsgs.setSendTimeStamp(c.getString(c.getColumnIndex("_send_time_stamp")));
			chatmsgs.setDeliveredTimeStamp(c.getString(c.getColumnIndex("_delivered_time_stamp")));
			chatmsgs.setContent(c.getString(c.getColumnIndex("_content")));
			chatmsgs.setImgMsgImageUrl(c.getString(c.getColumnIndex("_img_msg_image_url")));
			chatmsgs.setImgMsgImageWidth(c.getInt(c.getColumnIndex("_img_msg_image_width")));
			chatmsgs.setImgMsgImageHeight(c.getInt(c.getColumnIndex("_img_msg_image_height")));
			chatmsgs.setNowJoinUserId(c.getInt(c.getColumnIndex("_now_join_user_id")));
			chatmsgs.setNotificationBaseContent(c.getString(c.getColumnIndex("_notification_base_content")));
			chatmsgs.setNotificationActyContent(c.getString(c.getColumnIndex("_notification_acty_content")));
			chatmsgs.setNotificationActyTitle(c.getString(c.getColumnIndex("_notification_acty_title")));
			chatmsgs.setNotificationActyTitleSub(c.getString(c.getColumnIndex("_notification_acty_title_sub")));
			
			
			list.add(chatmsgs);			
		}
		c.close();
		db.close();
		return list;
		
		
	}

}
