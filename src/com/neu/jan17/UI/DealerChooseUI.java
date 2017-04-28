package com.neu.jan17.UI;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import com.neu.jan17.data.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DealerChooseUI extends JFrame implements ActionListener {

    private JPanel bottomPanel;
    private JLabel headline, dealerNameLabel;
    private JComboBox dealerItem;
    private JButton selectDealer;
    private static String getDealerID = null;
    private JPanel dealerChooseUI;


    public DealerChooseUI() {

        headline = new JLabel("Dealer Choose");

        dealerNameLabel = new JLabel("Choose your dealer:");

        DealerData dd = new DealerData();
        String[] dealerID = new String[dd.getDealersData().length];
        for (int i = 0; i < dd.getDealersData().length; i++) {
            dealerID[i] = dd.getDealersData()[i].getId().substring(5, dd.getDealersData()[i].getId().length());
        }
        dealerItem = new JComboBox(dealerID);


        JButton selectDealer = new JButton("Confirm");
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setActionCommand("Back");
        selectDealer.addActionListener(this);
        selectDealer.setActionCommand("Confirm");
//        
//        selectDealer.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                getDealerID += "gmps-" + dealerItem.getSelectedItem();
//                // add your code here.
//                // redirect to team two page
//            }
//        });

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
        bottomPanel.add(backButton);
        bottomPanel.setBackground(new Color(235,245,251));
        add(bottomPanel);
//        setSize(200, 400);
        setSize(1500,1500);
        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public String getDealerID(){
        return getDealerID;
    }

    public String getDealerID(){
        return getDealerID;
    }

    public static void main(String[] args) {
        DealerChooseUI dealerChooseUI = new DealerChooseUI();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton backButton = (JButton) e.getSource();
		JButton selectDealer = (JButton)e.getSource();
        if (e.getActionCommand().equals("Back")) {
            // redirect to the main page
//        	DealerChooseUI().setVisible(false);
//            dispose();
            com.neu.jan17.UI.MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
        } else if (e.getActionCommand().equals("Confirm")) {
            // redirect to team 2's page
        }
		
	}
}
