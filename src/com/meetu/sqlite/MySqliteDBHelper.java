package com.meetu.sqlite;

import com.avos.avoscloud.DeleteCallback;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.StaticLayout;

public class MySqliteDBHelper extends SQLiteOpenHelper {
	
	private static final String DBNAME="meetu.db";
	private static final int VERSION=4;
	
	private static final String TBL_CHANNEL="channel";
	private static final String TBL_CHANNEL_COLUMN_ID="_id";
	private static final String TBL_CHANNEL_COLUMN_CODE="_code";
	private static final String TBL_CHANNEL_COLUMN_NAME="_name";
	private static final String TBL_CHANNEL_COLUMN_URL="_url";
	private static final String TBL_CHANNEL_COLUMN_STATE="_state";
	
	private static final String TBL_USERS="users";
	private static final String TBL_USERS_COLUMN_ID="_id";
	private static final String TBL_USERS_COLUMN_NAME="_name";
	private static final String TBL_USERS_COLUMN_PWD="_pwd";
	
	//聊天消息表
	private static final String TBL_CHATMSGS="chatmsgs";//表名
	private static final String TBL_CHATMSGS_MESSAGE_CACHE_ID="_message_cache_id";//本地消息缓存id
	private static final String TBL_CHATMSGS_MESSAGE_ID="_message_id";//
	private static final String TBL_CHATMSGS_CLIENT_ID="_client_id";//
	private static final String TBL_CHATMSGS_CONVERSATION_ID="_conversation_id";//属于哪个对话id
	private static final String TBL_CHATMSGS_CHAT_MSG_TYPE="_chat_msg_type";//消息类型
	private static final String TBL_CHATMSGS_CHAT_MSG_DIRECTION="_chat_msg_direction" ;//
	private static final String TBL_CHATMSGS_CHAT_MSG_STATUS="_chat_msg_status";//
	private static final String TBL_CHATMSGS_IS_SHOW_TIME="_is_show_time";//是否显示时间
	private static final String TBL_CHATMSGS_SEND_TIME_STAMP="_send_time_stamp";//
	private static final String TBL_CHATMSGS_DELIVERED_TIME_STAMP="_delivered_time_stamp";//
	private static final String TBL_CHATMSGS_CONTENT="_content";//消息内容
	private static final String TBL_CHATMSGS_IMG_MSG_IMAGE_URL="_img_msg_image_url";//
	private static final String TBL_CHATMSGS_IMG_MSG_IMAGE_WIDTH="_img_msg_image_width";//图片宽
	private static final String TBL_CHATMSGS_IMG_MSG_IMAGE_HIGHT="_img_msg_image_height";//图片高
	private static final String TBL_CHATMSGS_NOW_JOIN_USER_ID="_now_join_user_id";//新加入成员的id
	private static final String TBL_CHATMSGS_NOTIFICATION_BASE_CONTENT="_notification_base_content";//
	private static final String TBL_CHATMSGS_NOTIFICATION_ACTY_CONTENT="_notification_acty_content";//
	private static final String TBL_CHATMSGS_NOTIFICATION_ACTY_TITLE="_notification_acty_title";//
	private static final String TBL_CHATMSGS_NOTIFICATION_aCTY_TITLE_SUB="_notification_acty_title_sub";//
	
	
	//消息总表
	private static final String TBL_MESSAGES="messages";//表名
	private static final String TBL_MESSAGES_CACHEID="_messages_cache_id";//表名
	private static final String TBL_MESSAGES_CONVERSATION_ID="_conversation_id";
	private static final String TBL_MESSAGES_CONVERSATION_TYPE="_conversation_type";
	private static final String TBL_MESSAGES_CREATOR_ID="_creator_id";
	private static final String TBL_MESSAGES_UNREAD_MSG_COUNT="_unread_msg_count";
	private static final String TBL_MESSAGES_TIME_OVER="_time_over";
	private static final String TBL_MESSAGES_SCRIP_USER_ID="_scrip_user_id";
	private static final String TBL_MESSAGES_SCRIP_USER_NAME="_scrip_user_name";
	private static final String TBL_MESSAGES_ACTY_ID="_acty_id";
	private static final String TBL_MESSAGES_ACTY_NAME="_acty_name";
	private static final String TBL_MESSAGES_CHAT_ID="_chat_id";
	private static final String TBL_MESSAGES_CHAT_NAME="_chat_name";
	private static final String TBL_MESSAGES_PERSON_COUNT="_person_count";
	private static final String TBL_MESSAGES_PERSON_COUNT_UPDATE="_person_count_update";
	
	private static final String TBL_EMOJIS="emojis";//表名
	private static final String TBL_EMOJIS_CACHE_ID="_emojis_cache_id";//缓存id
	private static final String TBL_EMOJIS_ID="_emojis_id";//表情对应的本子资源图片的id
	private static final String TBL_EMOJIS_CHARACTER="_character";//表情对应的文字表述 例如【微笑】
	private static final String TBL_EMOJIS_FACE_NAME="_face_name";//表情资源文件名
	

//	public MySqliteDBHelper(Context context, String name, CursorFactory factory,
//			int version) {
//		super(context, name, factory, version);
//		// TODO Auto-generated constructor stub
//	}
	public MySqliteDBHelper (Context context){
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		StringBuffer sb=new StringBuffer();
		sb.append("create table if not exists ");
		sb.append(TBL_CHANNEL+"(");
		sb.append( TBL_CHANNEL_COLUMN_ID +" integer primary key autoincrement ,");
		sb.append(TBL_CHANNEL_COLUMN_CODE+ " varchar(100) ,");
		sb.append(TBL_CHANNEL_COLUMN_NAME+" varchar(100) ,");
		sb.append(TBL_CHANNEL_COLUMN_URL+" varchar(100),");
		sb.append(TBL_CHANNEL_COLUMN_STATE+" varchar(1000) ");
		sb.append(")");
		db.execSQL(sb.toString());
		
		StringBuffer sb2=new StringBuffer();
		sb2.append("create table if not exists ");
		sb2.append(TBL_USERS+"(");
		sb2.append( TBL_USERS_COLUMN_ID +" integer primary key autoincrement ,");
		sb2.append(TBL_USERS_COLUMN_NAME+ " varchar(100) ,");
		sb2.append(TBL_USERS_COLUMN_PWD+" varchar(100) ");		
		sb2.append(")");
		db.execSQL(sb2.toString());
		
		/**
		 * 聊天消息表
		 */
		StringBuffer sb3=new StringBuffer();
		sb3.append("create table if not exists ");
		sb3.append(TBL_CHATMSGS+"(");
		sb3.append( TBL_CHATMSGS_MESSAGE_CACHE_ID+" integer primary key autoincrement ,");
		sb3.append(TBL_CHATMSGS_MESSAGE_ID+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_CLIENT_ID+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_CONVERSATION_ID+" varchar(100) , ");		
		sb3.append(TBL_CHATMSGS_CHAT_MSG_TYPE+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_CHAT_MSG_DIRECTION+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_CHAT_MSG_STATUS+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_IS_SHOW_TIME+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_SEND_TIME_STAMP+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_DELIVERED_TIME_STAMP+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_CONTENT+" varchar(10000) , ");
		sb3.append(TBL_CHATMSGS_IMG_MSG_IMAGE_URL+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_IMG_MSG_IMAGE_WIDTH+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_IMG_MSG_IMAGE_HIGHT+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_NOW_JOIN_USER_ID+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_NOTIFICATION_BASE_CONTENT+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_NOTIFICATION_ACTY_CONTENT+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_NOTIFICATION_ACTY_TITLE+" varchar(100) , ");
		sb3.append(TBL_CHATMSGS_NOTIFICATION_aCTY_TITLE_SUB+" varchar(100) ");
		sb3.append(")");
		db.execSQL(sb3.toString());
		
		/**
		 * 消息总表
		 */
		StringBuffer sb4=new StringBuffer();
		sb4.append("create table if not exists ");
		sb4.append(TBL_MESSAGES+"(");
		sb4.append(TBL_MESSAGES_CACHEID+ " integer primary key autoincrement ,");
		sb4.append(TBL_MESSAGES_CONVERSATION_ID+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_CONVERSATION_TYPE+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_CREATOR_ID+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_UNREAD_MSG_COUNT+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_TIME_OVER+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_SCRIP_USER_ID+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_SCRIP_USER_NAME+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_ACTY_ID+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_ACTY_NAME+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_CHAT_ID+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_CHAT_NAME+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_PERSON_COUNT+ " varchar(100) ,");
		sb4.append(TBL_MESSAGES_PERSON_COUNT_UPDATE+ " varchar(100) ");
		sb4.append(")");
		db.execSQL(sb4.toString());
		
		/**
		 * emoji 聊天表情 表
		 */
		StringBuffer sb5=new StringBuffer();
		sb5.append("create table if not exists ");
		sb5.append(TBL_EMOJIS+"(");
		sb5.append(TBL_EMOJIS_CACHE_ID+ " integer primary key autoincrement ,");
		sb5.append(TBL_EMOJIS_ID+ " varchar(100) ,");
		sb5.append(TBL_EMOJIS_CHARACTER+ " varchar(100) ,");
		sb5.append(TBL_EMOJIS_FACE_NAME+ " varchar(100) ");
	
		sb5.append(")");
		db.execSQL(sb5.toString());
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
//		String sql1="drop table if exists "+TBL_CHANNEL;
//		String sql2="drop table if exists "+TBL_USERS;
//		String sql3="drop table if exists "+TBL_CHATMSGS;
//		String sql4="drop table if exists "+TBL_MESSAGES;
//		String sql5="drop table if exists "+TBL_EMOJIS;
//		db.execSQL(sql1);
//		db.execSQL(sql2);
//		db.execSQL(sql3);
//		db.execSQL(sql4);
//		db.execSQL(sql5);
		
		onCreate(db);
	}
	
	

}
