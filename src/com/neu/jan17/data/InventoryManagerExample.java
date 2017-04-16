package com.neu.jan17.data;

import java.io.FileNotFoundException;

public class InventoryManagerExample {
    /**
     * This method shows how to interact with InventoryManager.
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws Exception {
        // InventoryManager is an interface, BasicInventoryManager implements InventoryManager
        // The constructor of BasicInventoryManager takes the path to the data folder, and loads all inventory from files
        InventoryManager inventoryManager = new BasicInventoryManager("data");

        // Get an inventory by its dealer id
        Inventory inventory = inventoryManager.getInventoryByDealerId("gmps-aj-dohmann");

        // Create a new vehicle and add to an inventory given its dealer id
        Vehicle vehicle = new Vehicle("2345", 2014, "Chevrolet", "Silverado", "Crew Cab", Category.NEW, "Short TRUCK", 37692.0f);
        try {
            inventoryManager.addVehicleToInventory("gmps-aj-dohmann", vehicle);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // Remove a vehicle from an inventory given dealer id and vehicle id
        try {
            inventoryManager.removeVehicleFromInventory("gmps-aj-dohmann", "2345");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // Save changes to an inventory to its file given the path to the data folder and the inventory object
        inventoryManager.updateInventoryFile("data", inventory);
    }
}
