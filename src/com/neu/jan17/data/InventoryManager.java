package com.neu.jan17.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * @author Xueying Zhao
 */
public class InventoryManager {
    private Map<String, Inventory> inventoryMap = new HashMap<>();

    public InventoryManager(String dataFolderPath) throws FileNotFoundException {
        this.readInventoryFolder(dataFolderPath);
    }

    // read Inventory files from the data folder (except the dealers file)
    private void readInventoryFolder(String folderPath) throws FileNotFoundException {
        File folder = new File(folderPath);
        for (File fileEntry: folder.listFiles()) {
            if (fileEntry.isFile()) {
                if(!fileEntry.getName().equals("dealers")) {
                    Inventory inventory = getInventoryFromFile(fileEntry);
                    inventoryMap.put(inventory.getDealerId(), inventory);
                }
            }
        }
    }

    // read each Inventory from the file and construct Inventory Objects
    private Inventory getInventoryFromFile(File fileEntry) throws FileNotFoundException {
        Inventory inventory = new Inventory();
        FileReader fileReader = new FileReader(fileEntry);
        Scanner fileIn = new Scanner(fileReader);
        fileIn.nextLine(); // skip the first line of each Inventory file
        String dealerId = "";
        Collection<Vehicle> vehicles = new ArrayList<>();
        while(fileIn.hasNextLine()) {
            String[] vehicleInfo = fileIn.nextLine().split("~");
            if (dealerId.isEmpty()) {
                dealerId = vehicleInfo[1];
            }
            vehicles.add(new Vehicle(vehicleInfo[0], Integer.parseInt(vehicleInfo[3]), vehicleInfo[4],
                    vehicleInfo[5], vehicleInfo[6], Category.valueOf(vehicleInfo[2].toUpperCase()), vehicleInfo[7],
                    Float.parseFloat(vehicleInfo[8])));
        }
        inventory.setDealerId(dealerId);
        inventory.setVehicles(vehicles);
        fileIn.close();

        return inventory;
    }

    public void addVehicleToInventory(String dealerId, Vehicle vehicle) {
        Inventory targetInventory = inventoryMap.get(dealerId);
        targetInventory.getVehicles().add(vehicle);
    }

    public void removeVehicleFromInventory(String dealerId, String vehicleId) {
        Collection<Vehicle> targetVehicles = inventoryMap.get(dealerId).getVehicles();
        Vehicle vehicleToRemove = getVehicleById(targetVehicles, vehicleId);
        if (vehicleToRemove != null) {
            targetVehicles.remove(vehicleToRemove);
        } else {
            System.out.println("Can't find the vehicle by Id");
        }

    }

    public Inventory getInventoryByDealerId(String dealerId) {
        return inventoryMap.get(dealerId);
    }

    public Vehicle getVehicleById(Collection<Vehicle> vehicles, String vehicleId) {
        for (Vehicle vehicle: vehicles) {
            if (vehicle.getId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }
}
