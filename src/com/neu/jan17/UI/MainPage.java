package com.neu.jan17.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MainPage extends JFrame implements ActionListener {
    private JTextField title;
    private JButton customerButton;
    private JButton dealerButton;
    private JPanel MainPage = new JPanel();
    private DealerInfoTable dealerInfoTable = new DealerInfoTable();

    public MainPage() {
        dealerInfoTable.setVisible(false);
        setLayout(new BorderLayout());
        URL imgPathUrl = getClass().getResource("background.jpg");
        JLabel background = new JLabel(new ImageIcon(imgPathUrl.getPath()));
        add(background);
        background.setLayout(new FlowLayout());
        customerButton = new JButton("Customer");
        customerButton.setPreferredSize(new Dimension(100, 50));
        dealerButton = new JButton("Dealer");
        dealerButton.setPreferredSize(new Dimension(100,50));
        title = new JTextField("Vehicle Management");
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        title.setFont(font1);
        title.setEditable(false);
        title.setOpaque(false);
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        background.add(title);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(customerButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(200,0)));
        bottomPanel.add(dealerButton);
        add(bottomPanel, BorderLayout.SOUTH);

        //background.setLayout(new BoxLayout(background,BoxLayout.Y_AXIS));
        setTitle("Vehicle Management");
        setSize(1000, 500);
        customerButton.addActionListener(this);
        customerButton.setActionCommand("Customer");
        dealerButton.addActionListener(this);
        dealerButton.setActionCommand("Dealers");
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Customer")) {
            // redirect to Dealers Information

        } else if (e.getActionCommand().equals("Dealers")) {
            // redirect to Dealers Management Page
            //MainPage.setVisible(false);
            dispose();
            dealerInfoTable.setVisible(true);
        }
    }

    public static void main(String args[]) {
        MainPage main = new MainPage();
    }
}
