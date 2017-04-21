package com.neu.jan17.UI;


import com.neu.jan17.data.Dealer;
import com.neu.jan17.data.DealerData;
import com.neu.jan17.data.Vehicle;

import javax.swing.*;

public class VehicleDetailsController {


    /**
     *
     * @param vehicle is a vehicle object to be displayed
     */
    public  VehicleDetailsController(Vehicle vehicle){

        Dealer dealer = getDealer(vehicle.getWebId());
        Runnable runnable = () -> setDetails(new VehicleDetailsView(),vehicle, dealer);
        SwingUtilities.invokeLater(runnable);


    }

    /**
     *
     * @param webId uses webId of a vehicle object to get the associated dealer object
     * @return dealer assigned with the vehicle object
     */
    private Dealer getDealer(String webId) {

        DealerData dealerData = new DealerData();
        Dealer[] dealers = dealerData.getDealersData();
        for(Dealer dealer : dealers){
            if(dealer.getId().equals(webId)){
                return dealer;
            }
        }
        return null;
    }

    /**
     *
     * @param view is used as reference to VehicleDetailsView object which displays the frame
     * @param vehicle is vehicle object to be displayed
     * @param dealer is dealer object to be displaced
     */
    private void setDetails(VehicleDetailsView view, Vehicle vehicle, Dealer dealer){

        view.setPictureLabel(vehicle.getPhoto());
        view.setVehicleIDLabel("VEHICLE ID : "+ vehicle.getId() );
        view.setCategoryLabel("CATEGORY : "+ vehicle.getCategory());
        view.setMakeLabel("MAKE : "+ vehicle.getMake());
        view.setTrimLabel("TRIM : "+ vehicle.getTrim());
        view.setYearLabel("YEAR : "+ vehicle.getYear());
        view.setBodyTypeLabel("BODY TYPE : "+ vehicle.getBodyType());
        view.setModelLabel("MODEL : "+ vehicle.getModel());
        view.setPriceLabel("PRICE : "+vehicle.getPrice() );
        view.setDealerLocationLabel("LOCATION : " + dealer.getLocation());
        view.setDealerIDLabel("DEALER ID : "+ dealer.getId());
        view.setDealerURLLabel("URL : "+dealer.getUrl());

    }


}
