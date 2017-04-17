package com.neu.jan17.search;

import java.io.*;
import java.util.*;

import com.neu.jan17.data.Category;
import com.neu.jan17.data.Inventory;
import com.neu.jan17.data.InventoryManager;
import com.neu.jan17.data.Vehicle;

/**
 * Class that implements InventoryManager and provides methods to read and modify inventory information
 */
public class WorkingInventoryManager implements InventoryManager {
    private Map<String, Inventory> inventoryMap = new HashMap<>();

    public WorkingInventoryManager(String dataFolderPath) throws FileNotFoundException {
        this.readInventoryFolder(dataFolderPath);
    }

    /**
     * Read Inventory files from the data folder (except the dealers file)
     *
     * @param folderPath
     * @throws FileNotFoundException
     */
    private void readInventoryFolder(String folderPath) throws FileNotFoundException {
        File folder = new File(folderPath);
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                if (!fileEntry.getName().equals("dealers")) {
                    Inventory inventory = getInventoryFromFile(fileEntry);
                    inventoryMap.put(inventory.getDealerId(), inventory);
                }
            }
        }
    }

    /**
     * Read each Inventory from the file and construct Inventory Objects
     *
     * @param fileEntry
     * @return
     * @throws FileNotFoundException
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
            vehicles.add(new Vehicle(vehicleInfo[0], Integer.parseInt(vehicleInfo[3]), vehicleInfo[4],
                    vehicleInfo[5], vehicleInfo[6], Category.valueOf(vehicleInfo[2].toUpperCase()), vehicleInfo[7],
                    Float.parseFloat(vehicleInfo[8])));
        }
        inventory.setDealerId(dealerId);
        inventory.setVehicles(vehicles);
        fileIn.close();

        return inventory;
    }

    /**
     * {@inheritDoc}
     */
    public void addVehicleToInventory(String dealerId, Vehicle vehicle) throws IllegalArgumentException {
        Inventory targetInventory = inventoryMap.get(dealerId);
        Vehicle vehicleAdded = getVehicleById(targetInventory.getVehicles(), vehicle.getId());
        if (vehicleAdded == null) {
            targetInventory.getVehicles().add(vehicle);
        } else {
            throw new IllegalArgumentException("Vehicle with the same id is already in the Inventory.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeVehicleFromInventory(String dealerId, String vehicleId) {
        Collection<Vehicle> targetVehicles = inventoryMap.get(dealerId).getVehicles();
        Vehicle vehicleToRemove = getVehicleById(targetVehicles, vehicleId);
        if (vehicleToRemove != null) {
            targetVehicles.remove(vehicleToRemove);
        } else {
            throw new IllegalArgumentException(
                    String.format("Can not find the vehicle [%s] of dealer []: ", vehicleId, dealerId));
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
     * @param vehicleId
     * @return
     */
    private Vehicle getVehicleById(Collection<Vehicle> vehicles, String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void updateInventoryFile(String dataFolderPath, Inventory inventory) throws IOException {
        String dealerId = inventory.getDealerId();
        Collection<Vehicle> vehicles = inventory.getVehicles();
        String vehicleDetails = "id~webId~category~year~make~model~trim~type~price\n";
        for (Vehicle vehicle : vehicles) {
            vehicleDetails += vehicle.getId() + "~" + dealerId + "~" + vehicle.getCategory().toString().toLowerCase() + "~" + vehicle.getYear()
                    + "~" + vehicle.getMake() + "~" + vehicle.getModel() + "~" + vehicle.getTrim() + "~"
                    + vehicle.getBodyType() + "~" + vehicle.getPrice() + "\n";
        }
        // dealerId is the Inventory file name
        String inventoryFilePath = dataFolderPath + "/" + dealerId;
        write(inventoryFilePath, vehicleDetails);
    }

    /**
     * Write content to a file
     *
     * @param filePath
     * @param contentToWrite
     * @throws IOException
     */
    private static void write(String filePath, String contentToWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(contentToWrite);
        bufferedWriter.close();
    }
    
    //needed....
    public Map<String, Inventory> getAllVehicles() {
    	return inventoryMap;
    }
    
    
    

}
