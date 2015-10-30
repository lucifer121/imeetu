package com.meetu.entity;

import android.provider.ContactsContract.Contacts.Data;

public class Huodong {
	private String title;//活动标题
	private int id;
	private int img;//活动图片
	private int style;//风格分类
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	

}
