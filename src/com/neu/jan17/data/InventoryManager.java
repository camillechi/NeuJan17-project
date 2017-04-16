package com.neu.jan17.data;

import java.io.IOException;

public interface InventoryManager {
    /**
     * Add a new vehicle to an inventory; to save changes to files,
     * use {@link #updateInventoryFile(String, Inventory) updateInventoryFile} method.
     *
     * @param dealerId dealer id
     * @param vehicle  vehicle object
     */
    void addVehicleToInventory(String dealerId, Vehicle vehicle) throws IllegalArgumentException;

    /**
     * <p>Remove an existing vehicle from an inventory; to save changes to files,
     * use {@link #updateInventoryFile(String, Inventory) updateInventoryFile} method.
     *
     * @param dealerId  dealer id
     * @param vehicleId vehicle id
     */
    void removeVehicleFromInventory(String dealerId, String vehicleId);

    /**
     * Get an inventory by its dealer id.
     *
     * @param dealerId dealer id
     * @return the Inventory object of the dealer id
     */
    Inventory getInventoryByDealerId(String dealerId);

    /**
     * Update inventory file.
     *
     * @param dataFolderPath path to the folder that contains all data files
     * @param inventory      inventory object to be saved
     * @throws IOException
     */
    void updateInventoryFile(String dataFolderPath, Inventory inventory) throws IOException;
}
