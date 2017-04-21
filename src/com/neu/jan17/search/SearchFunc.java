package com.neu.jan17.search;

import java.util.List;

import com.neu.jan17.data.Vehicle;


public interface SearchFunc {
	/**
     * Search result by keyword from all vehicles
     *
     * @param kw	keyword
     * @return		false if nothing found, else true
     */
	public SearchResult searchByKeyWord(String kw);
	
	
	/**
     * Search result by keyword from a given collection of vehicles
     *
     * @param base	a collection of vehicles
     * @param kw	keyword
     * @return		false if nothing found, else true
     */
	public SearchResult searchByKeyWord(List<Vehicle> base, String kw);
	
	
	/**
     * Search result by filters from all vehicles
     *
     * @param filters	a list of filters
     * @return			false if nothing found, else true
     */
	public SearchResult searchByFilters(List<Filter> filters);
	
	
	/**
     * Search result by filters from a given collection of vehicles
     *
     * @param base		a collection of vehicles
     * @param filters	a list of filters
     * @return			false if nothing found, else true
     */
	public SearchResult searchByFilters(List<Vehicle> base, List<Filter> filters);
	
	
	/**
     * Get all vehicle data. Could be used to initialize the searching window
     *
     * @param     
     * @return    a list of vehicle items
     */
	public List<Vehicle> getData();
	
}
