package com.neu.jan17.search;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.neu.jan17.data.Inventory;
import com.neu.jan17.data.InventoryManager;
import com.neu.jan17.data.Vehicle;

public class SearchTool implements SearchFunc {
		
	private List<Vehicle> data;
	
	/**
     * Create a searching helper
     *
     * @param folderPath	the path that has all data files   
     */
	public SearchTool(String folderPath) throws FileNotFoundException {
		InventoryManager reader = new InventoryManager(folderPath);
		Map<String, Inventory> vehicleMap = reader.getInventoryMap();
		
		data = new ArrayList<Vehicle>();
		
		flatMapToList(vehicleMap, data);
	}
	

	@Override
	public SearchResult searchByKeyWord(List<Vehicle> base, String kw) {
		return new SearchResult(base.stream().filter(v -> matchKeyWord(v, kw.toLowerCase())).collect(Collectors.toList()));
	}
	
	@Override
	public SearchResult searchByKeyWord(String kw) {
		return searchByKeyWord(data, kw);
	}

	@Override
	public SearchResult searchByFilters(List<Vehicle> base, List<Filter> filters) {
		return new SearchResult(base.stream().filter(v -> matchFilters(v, filters)).collect(Collectors.toList()));
	}
	
	@Override
	public SearchResult searchByFilters(List<Filter> filters) {
		return searchByFilters(data, filters);
	}

	@Override
	public List<Vehicle> getData() {
		return data;
	}
	
	private boolean matchKeyWord(Vehicle v, String kw) {
		return v.getMake().indexOf(kw) >= 0 || v.getModel().indexOf(kw) >= 0 || v.getBodyType().indexOf(kw) >= 0 || v.getWebId().indexOf(kw) >= 0;
	}
	
	private boolean matchFilters(Vehicle v, List<Filter> filters) {
		return filters.stream().map((f) -> f.matchVehicle(v)).reduce(true, (x, y) -> (x && y));
	}
	
	private void flatMapToList(Map<String, Inventory> vehicleMap, List<Vehicle> data) {
		for (Inventory inventory : vehicleMap.values()) {
		    for(Vehicle v : inventory.getVehicles()) {
		    	data.add(v);
		    }
		}
	}

}
