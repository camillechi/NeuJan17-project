package com.neu.jan17.data;

import java.io.FileNotFoundException;
import java.util.Collection;

/**
 * Created by ZhaoXueYing on 4/13/17.
 */
public class Main {
    /**
     * The main method shows how to interact with InventoryManager class.
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        InventoryManager inventoryManager = new InventoryManager("data");

        Inventory inventory = inventoryManager.getInventoryByDealerId("gmps-aj-dohmann");
        if (inventory != null) {
            Collection<Vehicle> vehicles = inventory.getVehicles();
            for(Vehicle vehicle: vehicles) {
                System.out.println(vehicle.getId() + "*" + vehicle.getCategory() + "*" + vehicle.getYear() + "*" +
                        vehicle.getMake()+"*" + vehicle.getModel()+"*" + vehicle.getTrim() +"*" + vehicle.getPrice());
            }
        }
    }
}
