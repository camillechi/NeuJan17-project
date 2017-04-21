package com.neu.jan17.UI;

import com.neu.jan17.data.InventoryManager;
import com.neu.jan17.data.Vehicle;
import com.neu.jan17.data.SortInventory;
import com.neu.jan17.data.Category;

import com.neu.jan17.search.Filter;
import com.neu.jan17.search.LiteralFilter;
import com.neu.jan17.search.RangeFilter;
import com.neu.jan17.search.SearchTool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;


public class CustomerVehicleSearch extends JFrame implements CustomerVehicleSearchInterface {
    private static final long serialVersionUID = 1L;

    private JPanel leftPanel, rightPanel, topPanel, panelShow, panelPicture,panelData;
    private JButton homeBtn, viewDetails, searchBtn, searchFilter,jbpic;
    private JComboBox<String> filterYear, filterModel, filterMake, filterType, filterPrice, filterCategory, filterSort;

    private JLabel topPicture, vehicleLabel, titleLabel,yearLabel, modelLabel, makeLabel, typeLabel, priceLabel, categoryLabel;
    private String strPrice,strType,strCateg,strUrl,strYear,strModel,strMake,strTitle;
    private JTextField searchBar;
    private JScrollPane scrollPane;
    private List<Filter> filters;
    private SearchTool searchTool;
    private InventoryManager inventoryManager;

    private List<JComboBox> comboboxes;
    private List<JLabel> label;

    private GridBagConstraints gcrt;//for right panel

    private ImageIcon icn;

    private java.util.List<Vehicle> result ;
    private java.util.List<Vehicle> results;

    private int check=0;


    public CustomerVehicleSearch() throws Exception {

        super("Vehicle Search Screen");

        try {
            filters = new ArrayList<>();
            inventoryManager = new InventoryManager("data");
            searchTool = new SearchTool("data");
        } catch (Exception e) {
            System.out.println("Wrong file path!!");
        }
        setSize(1800, 1000);
        setLocation(10, 10);

        rightPanel = new JPanel();
        leftPanel = new JPanel();
        topPanel = new JPanel();
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(leftPanel, BorderLayout.WEST);
        c.add(rightPanel, BorderLayout.CENTER);
        c.add(topPanel, BorderLayout.NORTH);
        c.add(scrollPane, BorderLayout.CENTER);

        setRightPanel();
        setLeftPanel();
        setTopPanel();
        setLayout();
        addListener();


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    @Override
    public void setRightPanel() {

        rightPanel.setBackground(new Color(127, 179, 213));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        //Setting scrollpane
        scrollPane.setViewportView(rightPanel);

        //build the right panel

        gcrt = new GridBagConstraints();
        gcrt.insets = new Insets(3, 3, 2, 5);
        gcrt.weightx = 0.5;
        gcrt.weighty = 0.5;
        gcrt.anchor = GridBagConstraints.CENTER;
        rightPanel.setLayout(new GridBagLayout());
        if(check==0) {
            try {
                SearchTool st = new SearchTool("data");
                if (st.searchByKeyWord("doh")) {
                    result = st.getResult(0, 5);
                    //System.out.println(result.size());
                }
            } catch (Exception e) {
            }
        }
        if(StoreVaule.ve.size() != 0){
            result = StoreVaule.ve;
        }
        for (int i = 0; i < result.size(); i++) {

            strTitle = result.get(i).getMake()+result.get(i).getModel()+result.get(i).getYear();
            strPrice = "Price: $ " + result.get(i).getPrice();
            strType = "Type:" + result.get(i).getTrim();
            strCateg = "Categ:" + result.get(i).getCategory();
            strUrl = result.get(i).getPhoto();
            strYear=result.get(i).getYear().toString();
            strModel=result.get(i).getModel();
            strMake=result.get(i).getMake();

            //triggerVD = i;

            viewDetails = new JButton("View Details");
            viewDetails.setBackground(new Color(127, 179, 213));
         /*   viewDetails.addActionListener(new ActionListener() {
                JButton[] viewDetailsBtn = new JButton[results.size()];
       				for(int j=0;j<viewDetailsBtn.length;j++){
        				viewDetailsBtn[j].setText("View Details");
        				viewDetailsBtn[j].setEnabled(true);
        				viewDetailsBtn[j].setBackground(new Color(127, 179, 213));
        				viewDetailsBtn[j].add(rightPanel,BorderLayout.EAST);
        				viewDetailsBtn[j].addActionListener(new ActionListener(){
         				@Override
         				public void actionPerformed(ActionEvent arg0) {
          					new VehicleDetailsController(results.get(i));
         			}
       		});

            */

            yearLabel = new JLabel(strYear);
            yearLabel.setFont(new Font("Serif", Font.BOLD, 22));

            modelLabel = new JLabel(strModel);
            modelLabel.setFont(new Font("Serif", Font.BOLD, 22));

            makeLabel = new JLabel(strMake);
            makeLabel.setFont(new Font("Serif", Font.BOLD, 22));

            priceLabel = new JLabel(strPrice);
            priceLabel.setFont(new Font("Serif", Font.PLAIN, 18));
            typeLabel = new JLabel(strType);
            typeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            categoryLabel = new JLabel(strCateg);
            categoryLabel.setFont(new Font("Serif", Font.PLAIN, 15));

            panelShow = new JPanel();
            panelShow.setBackground(new Color(235, 245, 251));
            panelShow.setPreferredSize(new Dimension(900, 120));
            panelShow.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            panelData = new JPanel();
            panelData.setBackground(new Color(235, 245, 251));
            panelData.setPreferredSize(new Dimension(800, 120));
            panelData.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            panelData.setBorder(BorderFactory.createRaisedBevelBorder());

            panelPicture = new JPanel();
            panelPicture.setBackground(new Color(235, 245, 251));
            panelPicture.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            panelPicture.setBorder(BorderFactory.createRaisedBevelBorder());

            int centerGridy = 8;
            gcrt.gridx = 10;
            gcrt.gridy = centerGridy + i * 2;
            rightPanel.add(panelShow, gcrt);

            GridBagConstraints gcShow = new GridBagConstraints();
            gcShow.insets = new Insets(3, 3, 2, 5);
            gcShow.weightx = 0.5;
            gcrt.weighty = 0.5;
            gcShow.anchor = GridBagConstraints.CENTER;
            panelShow.setLayout(new GridBagLayout());

            gcShow.gridx = 0;
            gcShow.gridy = 0;
            panelShow.add(panelPicture, gcShow);
            gcShow.gridx = 1;
            gcShow.gridy = 0;
            panelShow.add(panelData, gcShow);

            try {
                URL url = new URL(strUrl);
                icn = new ImageIcon(url);
                jbpic = new JButton(icn);
                jbpic.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
                jbpic.setPreferredSize(new Dimension(90, 70));
                panelPicture.add(jbpic);
            } catch (Exception e) {
            }

            panelData.setLayout(new GridBagLayout());
            GridBagConstraints newgc = new GridBagConstraints();
            newgc.anchor = GridBagConstraints.LAST_LINE_START;
            newgc.insets = new Insets(2, 80, 4, 2);

            int gcGridy = 5;
            newgc.gridx = 0;
            newgc.gridy = gcGridy + i;
            panelData.add(yearLabel, newgc);

            newgc.gridx = 1;
            newgc.gridy = gcGridy + i;
            panelData.add(modelLabel, newgc);

            newgc.gridx = 2;
            newgc.gridy = gcGridy + i;
            panelData.add(makeLabel, newgc);

            newgc.gridx = 3;
            newgc.gridy = gcGridy + i;
            panelData.add(priceLabel, newgc);
            newgc.gridx = 3;
            newgc.gridy = gcGridy + i + 1;
            panelData.add(typeLabel, newgc);
            newgc.gridx = 3;
            newgc.gridy = gcGridy + i + 2;
            panelData.add(categoryLabel, newgc);
            newgc.anchor = GridBagConstraints.LAST_LINE_END;
            newgc.gridx = 5;
            newgc.gridy = gcGridy + i;
            panelData.add(viewDetails, newgc);

        }
        this.add(rightPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void setLeftPanel() {

        leftPanel.setBackground(new Color(127, 179, 213));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        Object items0[] = inventoryManager.getVehicleYearItems().toArray();
        filterYear = new JComboBox(items0);
        filterYear.setPreferredSize(new Dimension(180, 26));

        Object items[] = inventoryManager.getVehicleModelItems().toArray();
        filterModel = new JComboBox(items);
        filterModel.setPreferredSize(new Dimension(180, 26));

        Object items1[] = inventoryManager.getVehicleMakeItems().toArray();
        filterMake = new JComboBox(items1);
        filterMake.setPreferredSize(new Dimension(180, 26));

        Object items2[] = inventoryManager.getVehicleBodyTypeItems().toArray();
        filterType = new JComboBox(items2);
        filterType.setPreferredSize(new Dimension(180, 26));

        String[] price = {"Price:", "0-10,000", "10,000-20,000", "20,000-30,000", "30,000-40,000", "40,000-50,000", "50,000-60,000"};
        filterPrice = new JComboBox<String>(price);
        filterPrice.setPreferredSize(new Dimension(180, 26));

        String[] category = {"Category:", "NEW", "USED", "Certified"};
        filterCategory = new JComboBox<String>(category);
        filterCategory.setPreferredSize(new Dimension(180, 26));

        vehicleLabel = new JLabel("Search Vehicles");
        vehicleLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        topPicture = new JLabel("Space for picture");
        topPicture.setFont(new Font("Serif", Font.PLAIN, 40));


    }

    @Override

    public void setTopPanel() {

        searchBar = new JTextField(20);

        topPanel.setBackground(new Color(41, 128, 185));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        //Setting buttons
        searchBtn = new JButton("Search");
        searchBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
 
                try {
                    SearchTool st1 = new SearchTool("data");
                    if (st1.searchByKeyWord("Toyota")) {
                        result = st1.getResult(0, 5);
                        //System.out.println(result.size());
                        try {
                            StoreVaule.ve = result;
                            dispose();
                            CustomerVehicleSearch cvs = new CustomerVehicleSearch();
                        }
                        catch (Exception e1){
                        	 System.out.println("File1 does not exist.");
                        }
                        
                    }
                } catch (Exception e3) {
                	 System.out.println("File2 does not exist.");
                }
               
             }
        });
      
  

        searchFilter = new JButton("Search");
        searchFilter.setPreferredSize(new Dimension(180, 25));

        String[] sortBy = {"Sort By:", "Year Asc", "Year Desc", "Price Asc", "Price Desc"};
        filterSort = new JComboBox<String>(sortBy);
        filterSort.setPreferredSize(new Dimension(180, 26));

        filterSort.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    //List<Vehicle> r = new List<Vehicle>;

                    SortInventory sI = new SortInventory();

                    String selectedSort = (String) filterSort.getSelectedItem();
                    if (selectedSort.equals("Year Asc")) {
                        sI.sortVehiclesByYear(result);
                    }
                    if (selectedSort.equals("Year Desc")) {
                        sI.sortVehiclesByYearReverse(result);
                    }
                    if (selectedSort.equals("Price Asc")) {
                        sI.sortVehiclesByPrice(result);
                    }
                    if (selectedSort.equals("Price Desc")) {
                        sI.sortVehiclesByPriceReverse(result);
                    }

                    try {
                        StoreVaule.ve = result;
                        dispose();
                        CustomerVehicleSearch cvs = new CustomerVehicleSearch();
                    }
                    catch (Exception e1){

                    }

                }
            catch(
            Exception e1)

            {
                System.out.println("File does not exist.");
            }
        }
        });


        homeBtn = new JButton("Home");
        homeBtn.setPreferredSize(new Dimension(150, 30));
        homeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new MainPage();
            }

        });
    }


    public void setLayout() {
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.insets = new Insets(10, 30, 2, 15);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 3;
        leftPanel.add(vehicleLabel, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        leftPanel.add(filterModel, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        leftPanel.add(filterMake, gc);

        gc.gridx = 0;
        gc.gridy = 6;
        leftPanel.add(filterYear, gc);

        gc.gridx = 0;
        gc.gridy = 7;
        leftPanel.add(filterType, gc);

        gc.gridx = 0;
        gc.gridy = 8;
        leftPanel.add(filterPrice, gc);


        gc.gridx = 0;
        gc.gridy = 9;
        leftPanel.add(filterCategory, gc);

        gc.weighty = 30;
        gc.gridx = 0;
        gc.gridy = 10;
        leftPanel.add(searchFilter, gc);

        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc1 = new GridBagConstraints();

        gc1.insets = new Insets(50, 50, 20, 50);

        gc1.weightx = 0.5;
        gc1.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 2;
        topPanel.add(topPicture, gc);

        gc1.anchor = GridBagConstraints.LAST_LINE_END;
        gc1.gridx = 12;
        gc1.gridy = 4;
        topPanel.add(searchBar, gc1);

        gc1.anchor = GridBagConstraints.LAST_LINE_START;
        gc1.gridx = 14;
        gc1.gridy = 4;
        topPanel.add(searchBtn, gc1);

        gc1.gridx = 2;
        gc1.gridy = 4;
        topPanel.add(homeBtn, gc1);

        gc1.gridx = 5;
        gc1.gridy = 4;
        topPanel.add(filterSort, gc1);


    }

    public Comparable[] valueToRange(String name, String value) {
        Comparable[] range = new Comparable[2];
        if (name.equals("year")) {
            range = new Integer[2];
            if (value.indexOf(">") >= 0) {
                range[0] = Integer.parseInt(value.substring(1));
                range[1] = Integer.MAX_VALUE;
            } else if (value.indexOf("<") >= 0) {
                range[0] = Integer.MIN_VALUE;
                range[1] = Integer.parseInt(value.substring(1));
            } else {
                range[0] = Integer.parseInt(value);
                range[1] = (Integer) range[0] + 1;
            }
        } else if (name.equals("price")) {
            range = new Float[2];
            range[0] = Float.parseFloat(value.split("-")[0]);
            range[1] = Float.parseFloat(value.split("-")[1]);
        }
        return range;
    }

    public void addListener() {
            searchFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchTool st = null;
                try {
                    List<Filter> filters = new ArrayList<Filter>();
                    st = new SearchTool("data");

                    comboboxes = new ArrayList<JComboBox>();
                    comboboxes.add(filterYear);
                    comboboxes.add(filterMake);
                    comboboxes.add(filterModel);
                    comboboxes.add(filterType);
                    comboboxes.add(filterCategory);
                    comboboxes.add(filterPrice);
                } catch (Exception e1) {

                }

                for (int i = 0; i < 6; i++) {
                    if (!comboboxes.get(i).equals(0)) {
                        String value = comboboxes.get(i).toString();
                        String name = comboboxes.get(i).getName();
                        Filter f;
                        if (comboboxes.get(i).equals(filterPrice) || comboboxes.get(i).equals(filterYear)) {
                            Comparable[] range = valueToRange(name, value);
                            f = new RangeFilter(comboboxes.get(i).getName(), range[0], range[1]);
                        } else {
                            if (comboboxes.get(i).equals(filterCategory)) {
                                Category c;
                                switch (value) {
                                    case "NEW":
                                        c = Category.NEW;
                                        break;
                                    case "USED":
                                        c = Category.USED;
                                        break;
                                    default:
                                        c = Category.CERTIFIED;
                                }
                                f = new LiteralFilter(comboboxes.get(i).getName(), c);
                            } else {
                                f = new LiteralFilter(comboboxes.get(i).getName(), value);
                            }

                        }
                        filters.add(f);
                    }
                }
                if (st.searchByFilters(filters)) {
                    List<Vehicle> results = st.getResult();

                    for (int i = 0; i < results.size(); i++) {
                        Vehicle v = results.get(i);
                        yearLabel.setText(v.getYear().toString());
                        modelLabel.setText(v.getModel().toString());
                        makeLabel.setText(v.getMake().toString());
                        priceLabel.setText("Price: " + v.getPrice().toString());
                        typeLabel.setText("Body Type: " + v.getBodyType().toString());
                        categoryLabel.setText("Category: " + v.getCategory().toString());
                    }

                }


                try {
                	CustomerVehicleSearch cvs = new CustomerVehicleSearch();
                    check=1;
                }
                catch (Exception e1){

                }

            }

        });
    }


    public static void main(String[] args) throws Exception {

        //CustomerVehicleSearchScreen screen = new CustomerVehicleSearchScreen();
    	CustomerVehicleSearch cvs = new CustomerVehicleSearch();

    }


}