package com.meetu.entity;

import java.io.Serializable;

public class PhotoWall  implements Serializable{
	/*private String photoId;//照片id
	private String name;//照片名字
	private String source;//来源
	private String updateDate;//时间
	private int favourNumber;//点赞数量
	private int viewNumber;//浏览数量
	private String photoSetId;//用来计算图片请求url、
	private int length;
	private String image;//图片
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getFavourNumber() {
		return favourNumber;
	}
	public void setFavourNumber(int favourNumber) {
		this.favourNumber = favourNumber;
	}
	public int getViewNumber() {
		return viewNumber;
	}
	public void setViewNumber(int viewNumber) {
		this.viewNumber = viewNumber;
	}
	public String getPhotoSetId() {
		return photoSetId;
	}
	public void setPhotoSetId(String photoSetId) {
		this.photoSetId = photoSetId;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
*/
	private int id;
	private int img;
	
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
	public PhotoWall(int id, int img) {
		super();
		this.id = id;
		this.img = img;
	}
	
}
