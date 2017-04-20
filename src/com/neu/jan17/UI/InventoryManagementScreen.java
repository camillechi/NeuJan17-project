package com.neu.jan17.UI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.neu.jan17.data.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class InventoryManagementScreen extends JFrame {


    private JPanel totalPanel;
    private JPanel topPanel, midPanel, bottomPanel;
    private JTable inventoryData;
    private JScrollPane inventoryPane;
    private JLabel headline, dealerNameLabel;
    private JComboBox dealerItem;
    private JButton selectDealer;
    private JButton addButton;
    private JButton updateButton;

    protected Vehicles ve;

    public InventoryManagementScreen() {

        headline = new JLabel("Inventory Management");

        Vector<Vehicle> vehicleData = new Vector<>();

        dealerNameLabel = new JLabel("Please choose a dealer:");

        DealerData dd = new DealerData();
        String[] dealerID = new String[dd.getDealersData().length];
        for (int i = 0; i < dd.getDealersData().length; i++) {
            dealerID[i] = dd.getDealersData()[i].getId().substring(5, dd.getDealersData()[i].getId().length());
        }
        dealerItem = new JComboBox(dealerID);

        ve = new Vehicles();

        inventoryData = new JTable(new dealerModel(ve));
        inventoryData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
                    openEditUI(ve.showData(row), row);
                } else {
                    return;
                }
            }
        });
        inventoryData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resizeColumnWidth(inventoryData);
        inventoryPane = new JScrollPane(inventoryData);

        selectDealer = new JButton("Confirm");
        selectDealer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getDealerID = "";
                getDealerID += "gmps-" + dealerItem.getSelectedItem();
                try {
                    ve.removeAll();
                    for (Vehicle v : getVehicle(getDealerID).getVehicles()) {
                        ve.addData(v);
                    }
                    repaint();
                } catch (Exception unknowne) {
                }
            }
        });
        addButton = new JButton("  Add  ");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddUI();
            }
        });
        updateButton = new JButton("Update");

        topPanel = new JPanel();
        midPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.add(dealerNameLabel);
        topPanel.add(dealerItem);
        topPanel.add(selectDealer);
        midPanel.add(inventoryPane);
        bottomPanel.add(addButton);
        bottomPanel.add(updateButton);

        Font f1 = new Font("Meiryo UI", Font.PLAIN, 15);
        Font f2 = new Font("Meiryo UI", Font.PLAIN, 18);
        Font f3 = new Font("Meiryo UI", Font.PLAIN, 20);
        inventoryData.setRowHeight(25);
        inventoryData.setAutoCreateRowSorter(true);
        //inventoryData.setGridColor(Color.BLUE);
        Dimension tableSize = new Dimension(700, 600);
        inventoryPane.setPreferredSize(tableSize);

        Container con = getContentPane();
        setLayout(new BorderLayout(2, 2));
        con.add("North", topPanel);
        con.add("Center", midPanel);
        con.add("South", bottomPanel);

        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));
        midPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));

        changeFont(con, f2);
        //dealerNameLabel.setFont(f2);
        addButton.setFont(f3);
        updateButton.setFont(f3);
        Dimension boxsize = new Dimension(150, 30);
        dealerItem.setPreferredSize(boxsize);

        setVisible(true);
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public Inventory getVehicle(String id) throws Exception {
        InventoryManagerInterface imi = new InventoryManager("data");
        Inventory inventory = imi.getInventoryByDealerId(id);
        return inventory;
    }

    public void openAddUI() {
        new AddVehicle(this);
    }

    public void openEditUI(Vehicle vehicle, int row) {
        new AddVehicle(this, vehicle, row);
    }

    public void changeFont(Component component, Font font) {

        component.setFont(font);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                changeFont(child, font);
            }
        }
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 100; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 400)
                width = 400;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    class Vehicles {

        Vector<Vehicle> data = new Vector<>();

        Vehicles() {
        }

        public void addData(Vehicle vehicle) {
            data.add(vehicle);
        }

        public Vehicle showData(int row) {
            return data.get(row);
        }

        public void removeData(int row) {
            data.remove(row);
        }

        public void removeAll() {
            data.removeAllElements();
        }

        public void changeData(int row, Vehicle vehicle) {
            data.set(row, vehicle);
        }

    }

    class dealerModel extends AbstractTableModel {

        private Vehicles vehicle;
        private String[] name = {"Id", "Category", "Year", "Make", "Model", "Bodytype", "Price"};

        dealerModel(Vehicles vehicle) {
            this.vehicle = vehicle;
        }

        public int getRowCount() {
            return vehicle.data.size();
        }

        public int getColumnCount() {
            return 7;
        }

        public Object getValueAt(int row, int col) {
            Vehicle veh = vehicle.showData(row);
            if (col == 0) {
                return veh.getId();
            } else if (col == 1) {
                return veh.getCategory();
            } else if (col == 2) {
                return veh.getYear();
            } else if (col == 3) {
                return veh.getMake();
            } else if (col == 4) {
                return veh.getModel();
            } else if (col == 5) {
                return veh.getBodyType();
            } else {
                return veh.getPrice();
            }
        }

        public String getColumnName(int colIndex) {
            return name[colIndex];
        }

        public Class getColumnClass(int c) {
            return String.class;
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }

        public void setValueAt(String aValue, int row, int col) {

        }
    }

    public static void main(String[] args) {


        new InventoryManagementScreen();
    }
}
