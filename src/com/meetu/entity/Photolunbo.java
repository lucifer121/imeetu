package com.meetu.entity;

public class Photolunbo {
	private String title;//活动详细标题
	private int img;//轮播图片
//	private int id;//图片id
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public Photolunbo(String title, int img) {
		super();
		this.title = title;
		this.img = img;
	}
	
	

}
