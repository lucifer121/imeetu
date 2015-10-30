package com.meetu.entity;

public class Booking {
	private int id;//票的id
	private String name;//票的名字
	private int number;//票的数量
	private String price;//票的价格
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Booking(int id, String name, int number, String price) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.price = price;
	}
	public Booking() {
		super();
	}
	

}
