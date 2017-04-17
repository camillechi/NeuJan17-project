package com.neu.jan17.data;

import java.io.FileNotFoundException;
import java.util.*;

import com.neu.jan17.search.*;

public class SearchToolExample {
	public static void main(String[] args) {
		try {
			//create an object to help searching and pass in a pathname(absolute or relative)
			SearchTool s = new SearchTool("data");
			
			
			/*----------------------- search by keyword ------------------------*/
			
			//search with keyword "dohma"
			if(s.searchByKeyWord("dohma")) {
				//50 items per page
				int itemPerPage = 50;
							
				//get the first page of results
				List<ItemVehicle> first50Items = s.getResult(0, itemPerPage); // A ItemVehicle is just a Vehicle with an extra field "dealeId". You can treat it as a Vehicle
				System.out.println(first50Items.size() + " items found.");
				
				//get the second page
				List<ItemVehicle> second50Items = s.getResult(1, itemPerPage);
				System.out.println(second50Items.size() + " items found.");
				
				//get all results
				List<ItemVehicle> allResults = s.getResult();
				System.out.println(allResults.size() + " items found.");
			}
			
			/*----------------------- search by filters ------------------------*/
			
			//create filters
			List<Filter> filters = new ArrayList<Filter>();
			
			//create and add a range filter
			RangeFilter rf = new RangeFilter("year", 2005, 2012);
			filters.add(rf);
			//create and add a filter with single value
			LiteralFilter lf = new LiteralFilter("dealerId", "gmps-aj-dohmann");		
			filters.add(lf);
			
			//search for vehicles that are selling by dealer dohmann with year range of [2005, 2012) (2012 excluded)
			if(s.searchByFilters(filters)) {
				//get all results
				List<ItemVehicle> vehiclesListedByDealerDohmannWithYearRangeOf2005to2012 = s.getResult();
				System.out.println(vehiclesListedByDealerDohmannWithYearRangeOf2005to2012.size() + " items found.");
			}	
		} catch(FileNotFoundException e) {
			System.out.println("File does not exist.");
		}
	}
}
