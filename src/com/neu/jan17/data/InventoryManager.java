package com.neu.jan17.data;

import java.io.*;
import java.util.*;

/**
 * Class that implements InventoryManagerInterface and provides methods to read and modify inventory information
 */
public class InventoryManager implements InventoryManagerInterface {
    private Map<String , Inventory> inventoryMap = new HashMap<>();
    private Map<String, String> dealerFolderPathMap = new HashMap<>();

    public InventoryManager(String dataFolderPath) throws FileNotFoundException {
        this.readInventoryFolder(dataFolderPath);
    }

    /**
     * Read Inventory files from the data folder (except the dealers file)
     *
     * @param folderPath path to the directory of your data folder
     * @throws FileNotFoundException throws exception if there are no files in given directory
     */
    private void readInventoryFolder(String folderPath) throws FileNotFoundException {
        File folder = new File(folderPath);
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                if (!fileEntry.getName().equals("dealers")) {
                    Inventory inventory = getInventoryFromFile(fileEntry);
                    inventoryMap.put(inventory.getDealerId(), inventory);
                    dealerFolderPathMap.put(fileEntry.getName(),fileEntry.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Read each Inventory from the file and construct Inventory Objects
     *
     * @param fileEntry file containing input
     * @return returns inventory
     * @throws FileNotFoundException exception is thrown if no file is found
     */
    private Inventory getInventoryFromFile(File fileEntry) throws FileNotFoundException {
        Inventory inventory = new Inventory();
        FileReader fileReader = new FileReader(fileEntry);
        Scanner fileIn = new Scanner(fileReader);
        fileIn.nextLine(); // skip the first line of each Inventory file
        String dealerId = "";
        Collection<Vehicle> vehicles = new ArrayList<>();
        while (fileIn.hasNextLine()) {
            String[] vehicleInfo = fileIn.nextLine().split("~");
            if (dealerId.isEmpty()) {
                dealerId = vehicleInfo[1];
            }
            Vehicle v = new Vehicle();
            v.setAllDetails(vehicleInfo[0],vehicleInfo[1],Category.valueOf(vehicleInfo[2].toUpperCase()),
                    Integer.parseInt(vehicleInfo[3]),vehicleInfo[4],vehicleInfo[5],vehicleInfo[6],vehicleInfo[7],
                    Float.parseFloat(vehicleInfo[8]),vehicleInfo[9]);
            vehicles.add(v);
        }
        inventory.setDealerId(dealerId);
        inventory.setVehicles(vehicles);
        fileIn.close();

        return inventory;
    }

    /**
     * {@inheritDoc}
     */
    public void addVehicleToInventory(Dealer dealer, Vehicle vehicle) throws IllegalArgumentException {
        Inventory targetInventory = inventoryMap.get(dealer.getId());
        Vehicle vehicleAdded = getVehicleById(targetInventory.getVehicles(), vehicle);
        if (vehicleAdded == null) {
            targetInventory.getVehicles().add(vehicle);
        } else {
            throw new IllegalArgumentException("Vehicle with the same id is already in the Inventory.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeVehicleFromInventory(Dealer dealer, Vehicle vehicle) {
        Collection<Vehicle> targetVehicles = inventoryMap.get(dealer.getId()).getVehicles();
        Vehicle vehicleToRemove = getVehicleById(targetVehicles, vehicle);
        if (vehicleToRemove != null) {
            targetVehicles.remove(vehicle);
        } else {
            throw new IllegalArgumentException(
                    String.format("Can not find the vehicle [%s] of dealer [%s]: ", vehicle.getId(), dealer.getName()));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Inventory getInventoryByDealerId(String dealerId) {
        return inventoryMap.get(dealerId);
    }

    /**
     * Get a vehicle from a collection by vehicle id
     *
     * @param vehicles
     * @param vehicle
     * @return
     */
    private Vehicle getVehicleById(Collection<Vehicle> vehicles, Vehicle vehicle) {
        for (Vehicle vehicle_ : vehicles) {
            if (vehicle.getId().equals(vehicle_.getId())) {
                return vehicle_;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void updateInventoryToFile(Inventory inventory) throws Exception {
        String dealerId = inventory.getDealerId();
        Collection<Vehicle> vehicles = inventory.getVehicles();
        String vehicleDetails = "id~webId~category~year~make~model~trim~type~price\n";
        StringBuilder stringBuilder = new StringBuilder();
        for (Vehicle vehicle : vehicles) {
            stringBuilder.append(vehicle.toString()+"\n") ;
        }

        vehicleDetails = vehicleDetails+ stringBuilder.toString();
        // dealerId is the Inventory file name
        String inventoryFilePath = dealerFolderPathMap.get(dealerId);
        Util.writeToFile(inventoryFilePath, vehicleDetails,false);
    }



}
