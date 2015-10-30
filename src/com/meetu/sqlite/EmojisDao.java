package com.meetu.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.meetu.entity.ChatEmoji;
import com.meetu.entity.Chatmsgs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EmojisDao {
private  MySqliteDBHelper helper;
	
	public EmojisDao(Context context){
		helper=new MySqliteDBHelper(context);
	}
	/**
	 * 插入表情
	 * @param chatEmoji
	 */
	public void insert(ChatEmoji chatEmoji){
		SQLiteDatabase db=helper.getReadableDatabase();
		db.execSQL("insert into emojis values(null,?,?,?)"
				, new Object[]{chatEmoji.getId(),chatEmoji.getCharacter(),chatEmoji.getFaceName()});
		db.close();
		
	}
	/**
	 * 删除表情表
	 */
	public void deleteAll(){
		SQLiteDatabase db=helper.getReadableDatabase();
		db.execSQL("delete from emojis ");
		db.close();
	}
	
	/**
	 * 获得emoji表情的list对象
	 * @return
	 */
	public List<ChatEmoji> getChatEmojisList(){
		SQLiteDatabase db=helper.getReadableDatabase();
		Cursor c=db.rawQuery("select * from emojis ", new String[]{});
		List<ChatEmoji> list=new ArrayList<ChatEmoji>();
		while(c.moveToNext()){
		
			ChatEmoji chatEmoji=new ChatEmoji();
			
			chatEmoji.setId(c.getInt(c.getColumnIndex("_emojis_id")));
			chatEmoji.setCharacter(c.getString(c.getColumnIndex("_character")));
			chatEmoji.setFaceName(c.getString(c.getColumnIndex("_face_name")));		
			list.add(chatEmoji);			
		}
		c.close();
		db.close();
		return list;
	}

}
