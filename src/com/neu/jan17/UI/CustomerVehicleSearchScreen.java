package com.neu.jan17.UI;

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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class CustomerVehicleSearchScreen extends JFrame implements CustomerVehicleSearchInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel leftPanel,rightPanel,pane1,pane2,pane3,pane4,pane5,topPanel;
	private JButton homeBtn,viewDetails1,viewDetails2,viewDetails3,viewDetails4,viewDetails5,searchBtn,searchFilter;
	private JComboBox<String> filterYear,filterModel,filterMake,filterType,filterPrice,filterCategory,filterSort;
	private JLabel topPicture,vehicleLabel,label1,label2,label3,label4,label5,label6;
	private JLabel priceLabel1,priceLabel2,priceLabel3,priceLabel4,priceLabel5,priceLabel6;
	private JLabel typeLabel1,typeLabel2,typeLabel3,typeLabel4,typeLabel5,typeLabel6;
	private JLabel categoryLabel1,categoryLabel2,categoryLabel3,categoryLabel4,categoryLabel5,categoryLabel6;
	private JTextField searchBar;
	private JScrollPane scrollPane;
	
	public CustomerVehicleSearchScreen(){
		super("Vehicle Search Screen");
		setSize(1800,1000);
		setLocation(10,10);
		
		rightPanel = new JPanel();
		leftPanel = new JPanel();
		topPanel=new JPanel();
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(leftPanel,BorderLayout.WEST);
		c.add(rightPanel,BorderLayout.CENTER);
		c.add(topPanel,BorderLayout.NORTH);
		c.add(scrollPane,BorderLayout.CENTER);
		
		setRightPanel();
		setLeftPanel();
		setTopPanel();
		setLayout();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
		
	@Override
	public void setRightPanel() {
		
		rightPanel.setBackground(new Color(127,179,213));
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		//Setting scrollpane
		scrollPane.setViewportView(rightPanel);
		
		//Setting Panels
		pane1 = new JPanel();
		pane1.setBackground(new Color(235,245,251));
		pane1.setPreferredSize(new Dimension(1000, 120));

		pane2 = new JPanel();
		pane2.setBackground(new Color(235,245,251));
		pane2.setPreferredSize(new Dimension(1000, 120));

		pane3 = new JPanel();
		pane3.setBackground(new Color(235,245,251));
		pane3.setPreferredSize(new Dimension(1000, 120));

		pane4 = new JPanel();
		pane4.setBackground(new Color(235,245,251));
		pane4.setPreferredSize(new Dimension(1000, 120));

		pane5 = new JPanel();
		pane5.setBackground(new Color(235,245,251));
		pane5.setPreferredSize(new Dimension(1000, 120));
		
		
		viewDetails1 = new JButton("View Details");viewDetails1.setBackground(new Color(127,179,213));
		viewDetails2 = new JButton("View Details");viewDetails2.setBackground(new Color(127,179,213));
		viewDetails3 = new JButton("View Details");viewDetails3.setBackground(new Color(127,179,213));
		viewDetails4 = new JButton("View Details");viewDetails4.setBackground(new Color(127,179,213));
		viewDetails5 = new JButton("View Details");viewDetails5.setBackground(new Color(127,179,213));
		
		ViewDetailsListener viewDetails = new ViewDetailsListener();
		viewDetails1.addActionListener(viewDetails);
		viewDetails2.addActionListener(viewDetails);
		viewDetails3.addActionListener(viewDetails);
		viewDetails4.addActionListener(viewDetails);
		viewDetails5.addActionListener(viewDetails);
		
		//will be changed
		label1 = new JLabel("2017 Honda Accord");label1.setFont(new Font("Serif", Font.BOLD, 22));
		label2 = new JLabel("2017 Honda Coupe");label2.setFont(new Font("Serif", Font.BOLD, 22));
		label3 = new JLabel("2017 Honda Sedan");label3.setFont(new Font("Serif", Font.BOLD, 22));
		label4 = new JLabel("2017 Honda Hybrid");label4.setFont(new Font("Serif", Font.BOLD, 22));
		label5 = new JLabel("2017 Honda Odessey");label5.setFont(new Font("Serif", Font.BOLD, 22));
		label6 = new JLabel("2017 Honda Odessey");label6.setFont(new Font("Serif", Font.BOLD, 22));
		//will be changed
		priceLabel1 = new JLabel("MSRP: $31,000");priceLabel1.setFont(new Font("Serif", Font.PLAIN, 18));
		priceLabel2 = new JLabel("MSRP: $32,000");priceLabel2.setFont(new Font("Serif", Font.PLAIN, 18));
		priceLabel3 = new JLabel("MSRP: $33,000");priceLabel3.setFont(new Font("Serif", Font.PLAIN, 18));
		priceLabel4 = new JLabel("MSRP: $34,000");priceLabel4.setFont(new Font("Serif", Font.PLAIN, 18));
		priceLabel5 = new JLabel("MSRP: $35,000");priceLabel5.setFont(new Font("Serif", Font.PLAIN, 18));
		priceLabel6 = new JLabel("MSRP: $36,000");priceLabel6.setFont(new Font("Serif", Font.PLAIN, 18));
		//will be changed
		typeLabel1 = new JLabel("Type1:");typeLabel1.setFont(new Font("Serif", Font.PLAIN, 15));
		categoryLabel1 = new JLabel("Category1:");categoryLabel1.setFont(new Font("Serif", Font.PLAIN, 15));
		typeLabel2 = new JLabel("Type2:");typeLabel2.setFont(new Font("Serif", Font.PLAIN, 15));
		categoryLabel2 = new JLabel("Category1:");categoryLabel2.setFont(new Font("Serif", Font.PLAIN, 15));
		typeLabel3 = new JLabel("Type3:");typeLabel3.setFont(new Font("Serif", Font.PLAIN, 15));
		categoryLabel3 = new JLabel("Category1:");categoryLabel3.setFont(new Font("Serif", Font.PLAIN, 15));
		typeLabel4 = new JLabel("Type4:");typeLabel4.setFont(new Font("Serif", Font.PLAIN, 15));
		categoryLabel4 = new JLabel("Category1:");categoryLabel4.setFont(new Font("Serif", Font.PLAIN, 15));
		typeLabel5 = new JLabel("Type5:");typeLabel5.setFont(new Font("Serif", Font.PLAIN, 15));
		categoryLabel5 = new JLabel("Category1:");categoryLabel5.setFont(new Font("Serif", Font.PLAIN, 15));
		typeLabel6 = new JLabel("Type6:");typeLabel6.setFont(new Font("Serif", Font.PLAIN, 15));
		categoryLabel6 = new JLabel("Category1:");categoryLabel6.setFont(new Font("Serif", Font.PLAIN, 15));
	}
	
	@Override
	public void setLeftPanel() {
	
		
		leftPanel.setBackground(new Color(127,179,213));
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		String[] year = {"Year:","<2014","2015","2016","2017",">2017"};
		filterYear = new JComboBox<String>(year);
		filterYear.setPreferredSize(new Dimension(180,26));

		String[] model = {"Model:","Model1","Model2","Model3","Model4","Model5"};
		filterModel = new JComboBox<String>(model);
		filterModel.setPreferredSize(new Dimension(180,26));

		String[] make = {"Make:","Make1","Make2","Make3","Make4","Make5"};
		filterMake = new JComboBox<String>(make);
		filterMake.setPreferredSize(new Dimension(180,26));

		String[] type = {"Type:","Type1","Type2","Type3","Type4","Type5"};
		filterType = new JComboBox<String>(type);
		filterType.setPreferredSize(new Dimension(180,26));

		String[] price = {"Price:","0-10,000","10,000-20,000","20,000-30,000","30,000-40,000","40,000-50,000","50,000-60,000"};
		filterPrice = new JComboBox<String>(price);
		filterPrice.setPreferredSize(new Dimension(180,26));

		String[] category = {"Category:","Category1","Category2","Category3","Category4","Category5"};
		filterCategory = new JComboBox<String>(category);
		filterCategory.setPreferredSize(new Dimension(180,26));

		String[] sortBy = {"Sort By:","Year Asc","Year Desc","Price Asc","Price Desc"};
		filterSort = new JComboBox<String>(sortBy);
		filterSort.setPreferredSize(new Dimension(180,26));
		
		vehicleLabel = new JLabel("Search Vehicles");
		vehicleLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		topPicture = new JLabel("Space for picture");topPicture.setFont(new Font("Serif", Font.PLAIN, 40));
		
	}

	@Override
	public void setTopPanel() {
		
		searchBar= new JTextField(30);
		
		topPanel.setBackground(new Color(41, 128, 185));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		//Setting buttons
		searchBtn = new JButton("Search");
		
		searchFilter = new JButton("Search");
		searchFilter.setPreferredSize(new Dimension(180,25));
		
		homeBtn = new JButton("Home");
		homeBtn.setPreferredSize(new Dimension(150,30));
		homeBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage();
			}
			
		});
		
	}
	
	@Override
	public void setLayout() {
		leftPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.insets = new Insets(10,30,2,15);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.weightx = 0.5;
		gc.weighty = 0.5;

		gc.gridx = 0;gc.gridy = 3;
		leftPanel.add(vehicleLabel,gc);

		gc.gridx = 0;gc.gridy = 4;
		leftPanel.add(filterModel,gc);

		gc.gridx = 0;gc.gridy = 5;
		leftPanel.add(filterMake,gc);

		gc.gridx = 0;gc.gridy = 6;
		leftPanel.add(filterYear,gc);

		gc.gridx = 0;gc.gridy = 7;
		leftPanel.add(filterType,gc);

		gc.gridx = 0;gc.gridy = 8;
		leftPanel.add(filterPrice,gc);
		
		
		gc.gridx = 0;gc.gridy = 9;
		leftPanel.add(filterCategory,gc);
		
		gc.weighty = 30;
		gc.gridx = 0;gc.gridy = 10;
		leftPanel.add(searchFilter,gc);

		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc1 = new GridBagConstraints();

		gc1.insets = new Insets(50,50,20,50);

		gc1.weightx = 0.5;gc1.weighty = 0.5;

		gc.gridx = 0;gc.gridy = 2;
		topPanel.add(topPicture,gc);

		gc1.anchor = GridBagConstraints.LAST_LINE_END;
		gc1.gridx = 12;gc1.gridy = 4;
		topPanel.add(searchBar,gc1);

		gc1.anchor = GridBagConstraints.LAST_LINE_START;
		gc1.gridx = 14;gc1.gridy = 4;
		topPanel.add(searchBtn,gc1);

		gc1.gridx = 2;gc1.gridy = 4;
		topPanel.add(homeBtn,gc1);
		
		gc1.gridx = 5;gc1.gridy= 4;
		topPanel.add(filterSort, gc1);

		/*gc1.gridx = 9;gc1.gridy = 4;
		//topPanel.add(newVehiclesBtn,gc1);

		//gc1.gridx = 10;gc1.gridy = 4;
		//topPanel.add(viewDealersBtn,gc1);

		//gc1.gridx = 11;gc1.gridy = 4;
		//topPanel.add(aboutUsBtn,gc1);*/

		rightPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc2 = new GridBagConstraints();

		gc2.insets = new Insets(3,3,2,5);

		gc2.weightx = 0.5;gc2.weighty = 0.5;

		gc2.anchor = GridBagConstraints.CENTER;

		gc2.gridx = 10;gc2.gridy = 8;
		rightPanel.add(pane1,gc2);

		gc2.gridx = 10;gc2.gridy = 10;
		rightPanel.add(pane2,gc2);

		gc2.gridx = 10;gc2.gridy = 12;
		rightPanel.add(pane3,gc2);

		gc2.gridx = 10;gc2.gridy = 14;
		rightPanel.add(pane4,gc2);

		gc2.gridx = 10;gc2.gridy = 16;
		rightPanel.add(pane5,gc2);

		pane1.setLayout(new GridBagLayout());
		GridBagConstraints gc3 = new GridBagConstraints();
		gc3.anchor = GridBagConstraints.LAST_LINE_START;
		gc3.insets = new Insets(2,180,4,2);
		gc3.gridx = 3;gc3.gridy = 5;
		pane1.add(label1,gc3);
		gc3.gridx = 4;gc3.gridy = 5;
		pane1.add(priceLabel1,gc3);
		gc3.gridx = 4;gc3.gridy = 6;
		pane1.add(typeLabel1,gc3);
		gc3.gridx = 4;gc3.gridy = 7;
		pane1.add(categoryLabel1,gc3);
		gc3.anchor = GridBagConstraints.LAST_LINE_END;
		gc3.gridx = 8;gc3.gridy = 5;
		pane1.add(viewDetails1,gc3);

		pane2.setLayout(new GridBagLayout());
		GridBagConstraints gc4 = new GridBagConstraints();
		gc4.anchor = GridBagConstraints.LAST_LINE_START;
		gc4.insets = new Insets(2,180,4,2);
		gc4.gridx = 3;gc4.gridy = 6;
		pane2.add(label2,gc4);
		gc4.gridx = 4;gc4.gridy = 6;
		pane2.add(priceLabel2,gc4);
		gc4.gridx = 4;gc4.gridy = 7;
		pane2.add(typeLabel2,gc4);
		gc4.gridx = 4;gc4.gridy = 8;
		pane2.add(categoryLabel2,gc4);
		gc4.anchor = GridBagConstraints.LAST_LINE_END;
		gc4.gridx = 8;gc4.gridy = 6;
		pane2.add(viewDetails2,gc4);

		pane3.setLayout(new GridBagLayout());
		GridBagConstraints gc5 = new GridBagConstraints();
		gc5.anchor = GridBagConstraints.LAST_LINE_START;
		gc5.insets = new Insets(2,180,4,2);
		gc5.gridx = 3;gc5.gridy = 7;
		pane3.add(label3,gc5);
		gc5.gridx = 4;gc5.gridy = 7;
		pane3.add(priceLabel3,gc5);
		gc5.gridx = 4;gc5.gridy = 8;
		pane3.add(typeLabel3,gc5);
		gc5.gridx = 4;gc5.gridy = 9;
		pane3.add(categoryLabel3,gc5);
		gc5.anchor = GridBagConstraints.LAST_LINE_END;
		gc5.gridx = 8;gc5.gridy = 7;
		pane3.add(viewDetails3,gc5);

		pane4.setLayout(new GridBagLayout());
		GridBagConstraints gc6 = new GridBagConstraints();
		gc6.anchor = GridBagConstraints.LAST_LINE_START;
		gc6.insets = new Insets(2,180,4,2);
		gc6.gridx = 3;gc6.gridy = 8;
		pane4.add(label4,gc6);
		gc6.gridx = 4;gc6.gridy = 8;
		pane4.add(priceLabel4,gc6);
		gc6.gridx = 4;gc6.gridy = 9;
		pane4.add(typeLabel4,gc6);
		gc6.gridx = 4;gc6.gridy = 10;
		pane4.add(categoryLabel4,gc6);
		gc6.anchor = GridBagConstraints.LAST_LINE_END;
		gc6.gridx = 8;gc6.gridy = 8;
		pane4.add(viewDetails4,gc6);

		pane5.setLayout(new GridBagLayout());
		GridBagConstraints gc7 = new GridBagConstraints();
		gc7.anchor = GridBagConstraints.LAST_LINE_START;
		gc7.insets = new Insets(2,180,4,2);
		gc7.gridx = 3;gc7.gridy = 9;
		pane5.add(label5,gc7);
		gc7.gridx = 4;gc7.gridy = 9;
		pane5.add(priceLabel5,gc7);
		gc7.gridx = 4;gc7.gridy = 10;
		pane5.add(typeLabel5,gc7);
		gc7.gridx = 4;gc7.gridy = 11;
		pane5.add(categoryLabel5,gc7);
		gc7.anchor = GridBagConstraints.LAST_LINE_END;
		gc7.gridx = 8;gc7.gridy = 9;
		pane5.add(viewDetails5,gc7);

		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				new CustomerVehicleSearchScreen();

			}

		});

	}
}
