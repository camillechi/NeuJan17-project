package com.neu.jan17.search;

public class LiteralFilter extends Filter {
	private Object value;
	
	/**
     * Create a filter with single value
     *
     * @param name	name of the filter
     * @param value	value of the filter
     */
	public LiteralFilter(String name, Object value) {
		super(name);
		this.value = value;
	}
	
	@Override
	boolean matchVehicle(ItemVehicle v) {
		return v.get(getName()).equals(value);
	}

}
