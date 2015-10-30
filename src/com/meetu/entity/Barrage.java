package com.meetu.entity;

public class Barrage {
	private int id;//id
	private String name;//名字
	private int img;//头像图片
	private String content;//聊天内容
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Barrage(int id, String name, int img, String content) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.content = content;
	}
	public Barrage() {
		super();
	}
	

}
