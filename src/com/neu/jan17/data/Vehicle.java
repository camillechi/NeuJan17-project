package com.neu.jan17.data;

public class Vehicle {
	private String id;
	private Integer year;
	private String make;
	private String model;
	private String trim;
	private Category category;
	private String bodyType;
	private Float price;

	public Vehicle(String id, Integer year, String make, String model, String trim, Category category, String bodyType, Float price) {
		this.id = id;
		this.year = year;
		this.make = make;
		this.model = model;
		this.trim = trim;
		this.category = category;
		this.bodyType = bodyType;
		this.price = price;
	}

	// getters
	public String getId() {
		return id;
	}

	public Integer getYear() {
		return year;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getTrim() {
		return trim;
	}

	public Category getCategory() {
		return category;
	}

	public String getBodyType() {
		return bodyType;
	}

	public Float getPrice() {
		return price;
	}
}
