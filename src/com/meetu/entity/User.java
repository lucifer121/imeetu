package com.meetu.entity;

public class User {
	private String userid;
	private String name;
	private String sex;
	private String birthday;
	private String phone;
	private String daxue;
	private String zhuanye;
	private String school;
	
	private int headPhoto;//用户头像
	
	private Boolean isDetele;
	
	public Boolean getIsDetele() {
		return isDetele;
	}
	public void setIsDetele(Boolean isDetele) {
		this.isDetele = isDetele;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getUserid() {
		return userid;
	}
	public int getHeadPhoto() {
		return headPhoto;
	}
	public void setHeadPhoto(int headPhoto) {
		this.headPhoto = headPhoto;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDaxue() {
		return daxue;
	}
	public void setDaxue(String daxue) {
		this.daxue = daxue;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	

}
