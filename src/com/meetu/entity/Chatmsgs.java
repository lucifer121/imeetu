package com.meetu.entity;
/**
 * 聊天消息实体
 * @author Administrator
 *
 */

public class Chatmsgs {
	/**
	 * 消息缓存id
	 */
	private String messageCacheId;
	/**
	 * 消息id
	 */
	private String messageId;
	/**
	 * 用户id
	 */
	private String clientId;
	/**
	 * 消息对话id
	 */
	private String conversationId;
	/**
	 * 消息类型id
	 */
	private int chatMsgType;
	/**
	 * 消息的方向
	 */
	private int chatMsgDirection;
	/**
	 * 消息的状态
	 */
	private int chatMsgStatus;
	/**
	 * 是否显示时间
	 */
	private int isShowTime ;//是否显示时间
	/**
	 * 消息发送时间
	 */
	private String sendTimeStamp;
	/**
	 * 消息接收时间
	 */
	private String deliveredTimeStamp;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 图片消息的图片url
	 */
	private String imgMsgImageUrl;
	/**
	 *图片消息中图片的宽
	 */
	private int imgMsgImageWidth;
	/**
	 *图片消息中图片的高
	 */
	private int imgMsgImageHeight;
	/**
	 * 新加入成员的id
	 */
	private int nowJoinUserId;
	/**
	 * 文本通知消息的内容
	 */
	private String notificationBaseContent;
	/**
	 * 活动反馈通知消息的内容
	 */
	private String notificationActyContent;
	/**
	 * 活动反馈通知消息的标题
	 */
	private String notificationActyTitle;
	/**
	 * 活动反馈通知消息的子标题
	 */
	private String notificationActyTitleSub;
	
	public String getMessageCacheId() {
		return messageCacheId;
	}
	public void setMessageCacheId(String messageCacheId) {
		this.messageCacheId = messageCacheId;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getConversationId() {
		return conversationId;
	}
	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}
	public int getChatMsgType() {
		return chatMsgType;
	}
	public void setChatMsgType(int chatMsgType) {
		this.chatMsgType = chatMsgType;
	}
	public int getChatMsgDirection() {
		return chatMsgDirection;
	}
	public void setChatMsgDirection(int chatMsgDirection) {
		this.chatMsgDirection = chatMsgDirection;
	}
	public int getChatMsgStatus() {
		return chatMsgStatus;
	}
	public void setChatMsgStatus(int chatMsgStatus) {
		this.chatMsgStatus = chatMsgStatus;
	}
	public int getIsShowTime() {
		return isShowTime;
	}
	public void setIsShowTime(int isShowTime) {
		this.isShowTime = isShowTime;
	}
	public String getSendTimeStamp() {
		return sendTimeStamp;
	}
	public void setSendTimeStamp(String sendTimeStamp) {
		this.sendTimeStamp = sendTimeStamp;
	}
	public String getDeliveredTimeStamp() {
		return deliveredTimeStamp;
	}
	public void setDeliveredTimeStamp(String deliveredTimeStamp) {
		this.deliveredTimeStamp = deliveredTimeStamp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgMsgImageUrl() {
		return imgMsgImageUrl;
	}
	public void setImgMsgImageUrl(String imgMsgImageUrl) {
		this.imgMsgImageUrl = imgMsgImageUrl;
	}
	public int getImgMsgImageWidth() {
		return imgMsgImageWidth;
	}
	public void setImgMsgImageWidth(int imgMsgImageWidth) {
		this.imgMsgImageWidth = imgMsgImageWidth;
	}
	public int getImgMsgImageHeight() {
		return imgMsgImageHeight;
	}
	public void setImgMsgImageHeight(int imgMsgImageHeight) {
		this.imgMsgImageHeight = imgMsgImageHeight;
	}
	public int getNowJoinUserId() {
		return nowJoinUserId;
	}
	public void setNowJoinUserId(int nowJoinUserId) {
		this.nowJoinUserId = nowJoinUserId;
	}
	public String getNotificationBaseContent() {
		return notificationBaseContent;
	}
	public void setNotificationBaseContent(String notificationBaseContent) {
		this.notificationBaseContent = notificationBaseContent;
	}
	public String getNotificationActyContent() {
		return notificationActyContent;
	}
	public void setNotificationActyContent(String notificationActyContent) {
		this.notificationActyContent = notificationActyContent;
	}
	public String getNotificationActyTitle() {
		return notificationActyTitle;
	}
	public void setNotificationActyTitle(String notificationActyTitle) {
		this.notificationActyTitle = notificationActyTitle;
	}
	public String getNotificationActyTitleSub() {
		return notificationActyTitleSub;
	}
	public void setNotificationActyTitleSub(String notificationActyTitleSub) {
		this.notificationActyTitleSub = notificationActyTitleSub;
	}
	public Chatmsgs(String messageCacheId, String messageId, String clientId,
			String conversationId, int chatMsgType, int chatMsgDirection,
			int chatMsgStatus, int isShowTime, String sendTimeStamp,
			String deliveredTimeStamp, String content, String imgMsgImageUrl,
			int imgMsgImageWidth, int imgMsgImageHeight, int nowJoinUserId,
			String notificationBaseContent, String notificationActyContent,
			String notificationActyTitle, String notificationActyTitleSub) {
		super();
		this.messageCacheId = messageCacheId;
		this.messageId = messageId;
		this.clientId = clientId;
		this.conversationId = conversationId;
		this.chatMsgType = chatMsgType;
		this.chatMsgDirection = chatMsgDirection;
		this.chatMsgStatus = chatMsgStatus;
		this.isShowTime = isShowTime;
		this.sendTimeStamp = sendTimeStamp;
		this.deliveredTimeStamp = deliveredTimeStamp;
		this.content = content;
		this.imgMsgImageUrl = imgMsgImageUrl;
		this.imgMsgImageWidth = imgMsgImageWidth;
		this.imgMsgImageHeight = imgMsgImageHeight;
		this.nowJoinUserId = nowJoinUserId;
		this.notificationBaseContent = notificationBaseContent;
		this.notificationActyContent = notificationActyContent;
		this.notificationActyTitle = notificationActyTitle;
		this.notificationActyTitleSub = notificationActyTitleSub;
	}
	public Chatmsgs() {
		super();
	}
	


}
