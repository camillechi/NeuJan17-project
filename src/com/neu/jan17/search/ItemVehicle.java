package com.neu.jan17.search;

import com.neu.jan17.data.Category;
import com.neu.jan17.data.Vehicle;

public class ItemVehicle extends Vehicle {
	private final String dealerId;
	
	/**
     * Create a vehicle item. Actually it's just a vehicle with one more dealer attribute
     */
	public ItemVehicle(String id, Integer year, String make, String model, String trim, Category category, String bodyType, Float price, String dealerId) {
		super(id, year, make, model, trim, category, bodyType, price);
		this.dealerId = dealerId;
	}
	
	/**
     * Get the dealerId of the vehicle
     *
     * @param     
     * @return	the dealerId of the filter
     */
	public String getDealerId() {
		return dealerId;
	}
	
	Object get(String attrName) {
		switch(attrName) {
			case "year": return getYear();
			case "make": return getMake();
			case "model": return getModel();
			case "trim": return getTrim();
			case "category": return getCategory();
			case "bodyType": return getBodyType();
			case "price": return getPrice();
			case "dealerId": return getDealerId();
			default: return new Object();
		}
	}
	
}
