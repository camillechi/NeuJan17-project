package com.neu.jan17.UI;

import javax.swing.*;

import com.neu.jan17.data.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DealerChooseUI extends JFrame {

    private JPanel bottomPanel;
    private JLabel headline, dealerNameLabel;
    private JComboBox dealerItem;
    private JButton selectDealer;
    public static String getDealerID = null;
    private JPanel dealerChooseUI;


    public DealerChooseUI() {

        headline = new JLabel("Dealer Choose");

        dealerNameLabel = new JLabel("Please choose a dealer:");

        DealerData dd = new DealerData();
        String[] dealerID = new String[dd.getDealersData().length];
        for (int i = 0; i < dd.getDealersData().length; i++) {
            dealerID[i] = dd.getDealersData()[i].getId().substring(5, dd.getDealersData()[i].getId().length());
        }
        dealerItem = new JComboBox(dealerID);

        selectDealer = new JButton("Confirm");
        selectDealer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDealerID = "gmps-" + dealerItem.getSelectedItem();
                // add your code here.
                // redirect to team two page

                CustomerVehicleSearchScreen customerVehicleSearchScreen = new CustomerVehicleSearchScreen();
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(dealerNameLabel);
        bottomPanel.add(dealerItem);
        bottomPanel.add(selectDealer);
        add(bottomPanel);
        setVisible(true);
        setSize(200, 400);

    }

    public String getDealerID(){
        return getDealerID;
    }

    public static void main(String[] args) {
        DealerChooseUI dealerChooseUI = new DealerChooseUI();
    }
}
