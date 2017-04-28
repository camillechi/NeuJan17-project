package com.neu.jan17.UI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.neu.jan17.data.*;
import javafx.beans.binding.ObjectExpression;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;

public class InventoryManagementScreen extends JFrame {

    private JPanel topPanel, midPanel, rightPanel, bottomPanel;
    private dealerModel dm;
    private JTable inventoryData;
    private JScrollPane inventoryPane;
    private JCheckBox cb;
    private JLabel dealerNameLabel;
    private JComboBox dealerItem;
    private JButton selectDealer;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton selectAllButton;
    private JButton clearAllButton;
    private JButton deleteConfirmButton;
    private JButton cancelButton;

    protected Vehicles ve;

    public InventoryManagementScreen() {

        createComponent();
        createLayout();
        addListener();
        init();

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

    public void createComponent() {

        dealerNameLabel = new JLabel("Please choose a dealer:");
        dealerItem = new JComboBox(generateDealerInformation());

        ve = new Vehicles();
        dm = new dealerModel(ve);
        inventoryData = new JTable(dm);
        inventoryData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resizeColumnWidth(inventoryData);
        setHorizontal(inventoryData);
        inventoryPane = new JScrollPane(inventoryData);

        selectDealer = new JButton("Confirm");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        selectAllButton = new JButton("Select All");
        clearAllButton = new JButton("Clear All");
        deleteConfirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");

        topPanel = new JPanel();
        midPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
    }

    public void createLayout() {

        topPanel.add(dealerNameLabel);
        topPanel.add(dealerItem);
        topPanel.add(selectDealer);
        midPanel.add(inventoryPane);
        rightPanel.add(addButton);
        rightPanel.add(deleteButton);
        rightPanel.add(updateButton);
        bottomPanel.add(selectAllButton);
        bottomPanel.add(clearAllButton);
        bottomPanel.add(deleteConfirmButton);
        bottomPanel.add(cancelButton);

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
        con.add("East", rightPanel);
        con.add("South", bottomPanel);
        //bottomPanel.setVisible(false);

        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));
        midPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));
        rightPanel.setLayout(new GridLayout(20, 1));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        bottomPanel.setVisible(false);

        changeFont(con, f2);
        //dealerNameLabel.setFont(f2);
        addButton.setFont(f3);
        updateButton.setFont(f3);
        Dimension boxsize = new Dimension(150, 30);
        dealerItem.setPreferredSize(boxsize);

    }

    public void addListener() {

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

        MouseListener ml = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
                    ve.changeStatus(row);
                    repaint();
                }
            }
        };

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton.setEnabled(false);
                addButton.setEnabled(false);
                updateButton.setEnabled(false);
                dm.setHeader();
                ve.clearAllStatus();
                bottomPanel.setVisible(true);
                inventoryData.addMouseListener(ml);
                repaint();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddUI();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    saveData();
                }catch (Exception e1){

                }
            }
        });

        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ve.selectAllStatus();
                repaint();
            }
        });

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ve.clearAllStatus();
                repaint();
            }
        });

        deleteConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton.setEnabled(true);
                addButton.setEnabled(true);
                updateButton.setEnabled(true);
                ve.clearSelectVehicle();
                dm.setHeader();
                bottomPanel.setVisible(false);
                inventoryData.removeMouseListener(ml);
                repaint();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton.setEnabled(true);
                addButton.setEnabled(true);
                updateButton.setEnabled(true);
                dm.setHeader();
                repaint();
                bottomPanel.setVisible(false);
                inventoryData.removeMouseListener(ml);
            }
        });
    }

    public void setHorizontal(JTable table){
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();

        r.setHorizontalAlignment(JLabel.CENTER);
        hr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.getTableHeader().setDefaultRenderer(hr);
    }

    public void saveData() throws Exception{
        InventoryManager im = new InventoryManager("data");
        Inventory inventory = new Inventory();

        inventory.setVehicles(ve.updateVehicle());
        String getDealerID = "";
        getDealerID += "gmps-" + dealerItem.getSelectedItem();
        inventory.setDealerId(getDealerID);
        im.updateInventoryToFile(inventory);
    }

    public void init() {

        setVisible(true);
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public String[] generateDealerInformation() {

        DealerData dd = new DealerData();
        String[] dealerID = new String[dd.getDealersData().length];
        for (int i = 0; i < dd.getDealersData().length; i++) {
            dealerID[i] = dd.getDealersData()[i].getId().substring(5, dd.getDealersData()[i].getId().length());
        }

        return dealerID;

    }

    class Vehicles {

        //Vector<Vehicle> data = new Vector<>();
        Vector<Object[]> comboData = new Vector<>();

        Vehicles() {
        }

        public void addData(Vehicle vehicle) {
            Object[] temp = {false, vehicle};
            comboData.add(temp);
            //data.add(vehicle);
        }

        public int getRows() {
            return comboData.size();
        }

        public Vehicle showData(int row) {
            return (Vehicle) comboData.get(row)[1];
        }

        public void removeData(int row) {
            //data.remove(row);
            comboData.remove(row);
        }

        public void removeAll() {
            //data.removeAllElements();
            comboData.removeAllElements();
        }

        public void changeData(int row, Vehicle vehicle) {
            //data.set(row, vehicle);
            Object[] temp = {false, vehicle};
            comboData.set(row, temp);
        }

        public Object showStatus(int row) {
            return comboData.get(row)[0];
        }

        public void changeStatus(int row) {
            if (comboData.get(row)[0].equals(false)) {
                Object[] temp = {true, comboData.get(row)[1]};
                comboData.set(row, temp);
            } else {
                Object[] temp = {false, comboData.get(row)[1]};
                comboData.set(row, temp);
            }
        }

        public void selectAllStatus() {
            for (int i = 0; i < comboData.size(); i++) {
                if (comboData.get(i)[0].equals(false)) {
                    Object[] temp = {true, comboData.get(i)[1]};
                    comboData.set(i, temp);
                }
            }
        }

        public void clearAllStatus() {
            for (int i = 0; i < comboData.size(); i++) {
                if (comboData.get(i)[0].equals(true)) {
                    Object[] temp = {false, comboData.get(i)[1]};
                    comboData.set(i, temp);
                }
            }
        }

        public void clearSelectVehicle() {
            int i = 0;
            while (i < comboData.size()) {
                if (comboData.get(i)[0].equals(true)) {
                    comboData.remove(i);
                } else {
                    i++;
                }
            }
        }

        public ArrayList<Vehicle> updateVehicle(){
            Inventory inventory = new Inventory();
            ArrayList<Vehicle> a = new ArrayList<>();

            for (Object[] o:comboData){
                a.add((Vehicle)o[1]);
            }

            return a;
        }

    }

    class dealerModel extends AbstractTableModel {

        private Vehicles vehicle;
        private String[] name = {"Id", "Category", "Year", "Make", "Model", "Bodytype", "Price"};

        dealerModel(Vehicles vehicle) {
            this.vehicle = vehicle;
        }

        public int getRowCount() {
            return vehicle.comboData.size();
        }

        public int getColumnCount() {
            return name.length;
        }

        public Object getValueAt(int row, int col) {
            Vehicle veh = vehicle.showData(row);
            if (name.length == 8) {
                if (col == 1) {
                    return veh.getId();
                } else if (col == 2) {
                    return veh.getCategory();
                } else if (col == 3) {
                    return veh.getYear();
                } else if (col == 4) {
                    return veh.getMake();
                } else if (col == 5) {
                    return veh.getModel();
                } else if (col == 6) {
                    return veh.getBodyType();
                } else if (col == 7) {
                    return veh.getPrice();
                } else {
                    return vehicle.showStatus(row);
                }
            } else {
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
        }

        public String getColumnName(int colIndex) {
            return name[colIndex];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }

        public void setHeader() {
            if (name.length == 7) {
                String[] temp = {"Status", "Id", "Category", "Year", "Make", "Model", "Bodytype", "Price"};
                name = temp;
                inventoryData.getColumnModel().getColumn(0).setHeaderValue("Status");
                inventoryData.getColumnModel().getColumn(1).setHeaderValue("Id");
                inventoryData.getColumnModel().getColumn(2).setHeaderValue("Category");
                inventoryData.getColumnModel().getColumn(3).setHeaderValue("Year");
                inventoryData.getColumnModel().getColumn(4).setHeaderValue("Make");
                inventoryData.getColumnModel().getColumn(5).setHeaderValue("Model");
                inventoryData.getColumnModel().getColumn(6).setHeaderValue("Bodytype");
            } else {
                String[] temp = {"Id", "Category", "Year", "Make", "Model", "Bodytype", "Price"};
                name = temp;
                inventoryData.getColumnModel().getColumn(0).setHeaderValue("Id");
                inventoryData.getColumnModel().getColumn(1).setHeaderValue("Category");
                inventoryData.getColumnModel().getColumn(2).setHeaderValue("Year");
                inventoryData.getColumnModel().getColumn(3).setHeaderValue("Make");
                inventoryData.getColumnModel().getColumn(4).setHeaderValue("Model");
                inventoryData.getColumnModel().getColumn(5).setHeaderValue("Bodytype");
                inventoryData.getColumnModel().getColumn(6).setHeaderValue("Price");
            }
        }
    }

    public static void main(String[] args) {

        new InventoryManagementScreen();
    }
}
