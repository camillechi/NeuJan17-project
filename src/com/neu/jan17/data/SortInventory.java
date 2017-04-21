package com.neu.jan17.data;

import java.util.*;

// Sort function for Inventory
public class SortInventory {
    public void sortVehiclesByYear(List<Vehicle> vehicle) {
        vehicle.sort(Comparator.comparing(Vehicle::getYear));
    }

    public void sortVehiclesByYearReverse(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getYear).reversed());
    }

    public void sortVehiclesByModel(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getModel));
    }

    public void sortVehiclesByCategory(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getCategory));
    }

    public void sortVehiclesByPrice(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getPrice));
    }

    public void sortVehiclesByPriceReverse(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getPrice).reversed());
    }

    public void sortVehiclesByMake(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getMake));
    }

    public void sortVehiclesById(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getId));
    }

    public void sortVehiclesByTrim(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getTrim));
    }

    public void sortVehiclesByBodyType(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getBodyType));
    }

    public void sortVehiclesByWebId(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getWebId));
    }
}
