package com.meetu.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.meetu.entity.Chatmsgs;
import com.meetu.entity.Messages;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MessagesDao {
private  MySqliteDBHelper helper;

private ChatmsgsDao chatmsgsDao;
	
	public MessagesDao(Context context){
		helper=new MySqliteDBHelper(context);
//		chatmsgsDao=new ChatmsgsDao(context);
	}
	/**
	 * 插入数据
	 * @param messages 消息表实体 Bean
	 */
	public void insert(Messages messages){
		
		SQLiteDatabase db=helper.getReadableDatabase();
		db.execSQL("insert into messages values(null," +
				"?,?,?,?,?," +
				"?,?,?,?,?" +
				",?,?,?)", 
				new Object[]{messages.getConversationID(),messages.getConversationType(),messages.getCreatorID(),messages.getUnreadMsgCount(),messages.getTimeOver(),
				messages.getScripUserId(),messages.getScripUserName(),messages.getActyId(),messages.getActyName(),messages.getChatId(),
				messages.getChatName(),messages.getPersonCount(),messages.getPersonCountUpdate()
				});
		db.close();
	}
	
	public void deleteAll(){
		SQLiteDatabase db=helper.getReadableDatabase();
		String sql="delete from messages ";
		db.execSQL(sql);
		db.close();
	}
	
	public List<Messages> getMessages(){
		SQLiteDatabase db=helper.getReadableDatabase();
		Cursor c=db.rawQuery("select * from messages ", new String[]{});
		
		List<Messages> list=new ArrayList<Messages>();
		while(c.moveToNext()){
			Messages messages=new Messages();
			
			messages.setConversationID(c.getString(c.getColumnIndex("_conversation_id")));
			messages.setConversationType(c.getString(c.getColumnIndex("_conversation_type")));
			messages.setCreatorID(c.getString(c.getColumnIndex("_creator_id")));
			messages.setUnreadMsgCount(c.getInt(c.getColumnIndex("_unread_msg_count")));
			messages.setTimeOver(c.getLong(c.getColumnIndex("_time_over")));
			messages.setScripUserId(c.getString(c.getColumnIndex("_scrip_user_id")));
			messages.setScripUserName(c.getString(c.getColumnIndex("_scrip_user_name")));
			messages.setActyId(c.getString(c.getColumnIndex("_acty_id")));
			messages.setActyName(c.getString(c.getColumnIndex("_acty_name")));
			messages.setChatId(c.getString(c.getColumnIndex("_chat_id")));
			messages.setChatName(c.getString(c.getColumnIndex("_chat_name")));
			messages.setPersonCount(c.getInt(c.getColumnIndex("_person_count")));
			messages.setPersonCountUpdate(c.getLong(c.getColumnIndex("_person_count_update")));
		
			list.add(messages);			
		}
		c.close();
		db.close();
		
		return list;
	}
	
	
	
	
	

}
