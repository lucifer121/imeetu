package com.meetu.cloud.wrap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.meetu.activity.miliao.ChatGroupActivity;
import com.meetu.entity.Chatmsgs;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;

public class ObjChatMessage {
	/**
	 * 创建与服务器连接,登录
	 * @param client
	 */
	public void connectToChatServer(AVIMClient client){
		client.open(new AVIMClientCallback() {

			@Override
			public void done(AVIMClient client, AVIMException e) {
				if(e == null){
					//返回连接成功   --》创建聊天

				}
			}

		});
	}
	/**
	 * 创建或者查询一个已有的对话
	 * @param members 对话的成员
	 * @param members 对话的成员
	 * @param name 对话的名字
	 * @param attributes 对话的额外属性
	 * @param isTransient 是否是暂态对话
	 * @param isUnique 如果已经存在符合条件的对话，是否返回已有对话
	 *                 为 false 时，则一直为创建新的对话
	 *                 为 true 时，则先查询，如果已有符合条件的对话，则返回已有的，否则，创建新的并返回
	 *                 为 true 时，仅 members 为有效查询条件
	 * @param callback
	 */
	public void createChat(AVIMClient client,List<String> members,String name,Map<String, Object> attributes,boolean isTransient,boolean isUnique){
		client.createConversation(members, name, attributes,isTransient,isUnique,
				new AVIMConversationCreatedCallback() {

			@Override
			public void done(AVIMConversation conversation, AVIMException e) {
				if(e == null){
					//返回成功， conversation -- 》发送消息
				}
			}});
	}
	/**
	 * 发送消息
	 * @param conversation
	 * @param message
	 */
	public void sendChatMsg(AVIMConversation conversation,Chatmsgs message){
		AVIMTextMessage msg = new AVIMTextMessage();
		msg.setText(message.getContent());
		// 发送消息
		conversation.sendMessage(msg, new AVIMConversationCallback() {

			@Override
			public void done(AVIMException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					//发送成功
				}
			}

		});

	}
	/**
	 * 加入对话
	 * @param ConversationId 对话的 id
	 */
	public void joinChat(AVIMClient client,String ConversationId){
		//登录成功
		AVIMConversation conv = client.getConversation(ConversationId);
		conv.join(new AVIMConversationCallback(){

			@Override
			public void done(AVIMException e) {
				if(e == null){
					//加入成功
				}
			}

		});

	}
	/**接收消息处理
	 * 
	 * @author meetu
	 *
	 */
	class ReceiveMsgHandler extends AVIMMessageHandler{
		@Override
		public void onMessage(AVIMMessage message,
				AVIMConversation conversation, AVIMClient client) {
			// TODO Auto-generated method stub
			super.onMessage(message, conversation, client);
		}
	}
	/**注销聊天
	 * 
	 * @param client
	 */
	public void logOutChat(AVIMClient client){
		client.close(new AVIMClientCallback() {

			@Override
			public void done(AVIMClient arg0, AVIMException arg1) {
				// TODO Auto-generated method stub

			}
		});
	}
}
