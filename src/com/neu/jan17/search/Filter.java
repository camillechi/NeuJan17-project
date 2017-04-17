package com.neu.jan17.search;

public abstract class Filter {
	private String name;

	Filter(String name) {
		this.name = name;
	}
	
	/**
     * Get the name of the filter
     *
     * @param     
     * @return	name of the filter
     */
	public String getName() {
		return name;
	}
	
	/**
     * Test if a vehicle item should be listed in search results
     *
     * @param v	a vehicle
     * @return	if should be listed
     */
	abstract boolean matchVehicle(ItemVehicle v);
}
