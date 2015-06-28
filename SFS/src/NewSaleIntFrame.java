import javax.swing.JInternalFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JList;


public class NewSaleIntFrame extends JInternalFrame implements ActionListener,FocusListener  {
	private static final String MAINPANEL = "Main Panel" ;
	private ProductManager productManager;
	private CustomerManager customerManager ;
	private JPanel newSalePanel;
	private JPanel bottomButtonPanel;
	private JButton btnAddToCart;
	private CardLayout cardLayout;
	private Sale newSale ;
	private JSplitPane splitPane;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JComboBox<String> comboBox;
	private JPanel panel_2;
	private JTextField searchField;
	private JButton btnSearch;
	private JTable tempTable , finalTable;
	private DefaultTableModel tempTableModel ,finalTableModel ;
	private JButton btnCommit;
	private JPanel panel_3;
	private JLabel lblTotalCost;
	private JTextField txtTotalCost;
	private JLabel lblCustomerCart;
	private ArrayList<Product> productsInCart ;
	private SalesManager salesManager ;
	public NewSaleIntFrame(ProductManager productManager,CustomerManager customerManager,SalesManager salesManager) {
		this.productManager = productManager ;
		this.customerManager = customerManager ;
		this.salesManager =salesManager ;
		
		productsInCart = new ArrayList<Product>() ;
		
		
		BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
		Container north = (Container)ui.getNorthPane();
		north.remove(0);
		north.validate();
		north.repaint();
		
		paintFrame();
	}
	
	public void paintFrame(){
		cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);
		
		newSalePanel = new JPanel();
		newSalePanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(newSalePanel,MAINPANEL);
		
		
		JPanel topSearch_Panel = new JPanel();
		newSalePanel.add(topSearch_Panel, BorderLayout.NORTH);
		topSearch_Panel.setLayout(new BoxLayout(topSearch_Panel, BoxLayout.X_AXIS));
		
		JLabel lblSearchForA = new JLabel("Customer :");
		topSearch_Panel.add(lblSearchForA);
		
		comboBox = new JComboBox();
		topSearch_Panel.add(comboBox);
		
		bottomButtonPanel = new JPanel();
		newSalePanel.add(bottomButtonPanel, BorderLayout.SOUTH);
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.addActionListener(this);
		bottomButtonPanel.add(btnAddToCart);
		
		btnCommit = new JButton("Commit");
		btnCommit.addActionListener(this);
		bottomButtonPanel.add(btnCommit);
		
		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		newSalePanel.add(splitPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		
		searchField = new JTextField();
		searchField.addFocusListener(this);
		searchField.setText("ID / Description");
		panel_2.add(searchField);
		searchField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		panel_2.add(btnSearch);
		
		panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);		
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		lblTotalCost = new JLabel("Total Cost :");
		panel_3.add(lblTotalCost);
		
		txtTotalCost = new JTextField();
		txtTotalCost.setEditable(false);
		panel_3.add(txtTotalCost);
		txtTotalCost.setColumns(10);
		
		lblCustomerCart = new JLabel("Customer Cart :");
		panel_1.add(lblCustomerCart, BorderLayout.NORTH);
		
		paintComboBox();
		createTempTable();
		createFinalTable();
	}
	public void initiateComponents(){
		newSale = new Sale();
		paintComboBox();
		
	}
	
	public void createTempTable(){
		String col[] = {"ID","Description","Price"};
		tempTableModel = new DefaultTableModel(col,0); 
		tempTable = new JTable(tempTableModel){
	    	 @Override
	            public boolean isCellEditable(int row , int column) {
	                return false ;
	            }
	    };
	    tempTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tempTable.getTableHeader().setReorderingAllowed(false);
	    ToolTipManager.sharedInstance().setDismissDelay(2000);
		scrollPane.setViewportView(tempTable);
	}
	
	public void fillTempTable(ArrayList<Product> tempList){
		tempTableModel.setNumRows(tempList.size());
		int j =0;
		for(Product p : tempList){
			tempTableModel.setValueAt(p.getId(),j,0);
			tempTableModel.setValueAt(p.getDescription(),j,1);
			tempTableModel.setValueAt(p.getPrice(),j,2);
		   	j++;
		}
		
	}
	
	
	public void createFinalTable(){
		String col[] = {"ID","Description","Price per Unit","Quantity"};
		finalTableModel = new DefaultTableModel(col,0); 
		finalTable = new JTable(finalTableModel){
	    	 @Override
	            public boolean isCellEditable(int row , int column) {
	                return false ;
	            }
	    };
	    finalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    finalTable.getTableHeader().setReorderingAllowed(false);
	    ToolTipManager.sharedInstance().setDismissDelay(2000);
	    scrollPane_1.setViewportView(finalTable);
	}
	
	public void addProductToFinalTable(Product p,int quantity){
		newSale.addProduct(p,quantity);
		
		String[] newRow={"","","",""}  ;
		newRow[0]=p.getId();
		newRow[1]=p.getDescription();
		newRow[2]=String.valueOf(p.getPrice());
		newRow[3]=String.valueOf(quantity) ;
		finalTableModel.addRow(newRow);
		
	}
	
	public void paintComboBox(){
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
		ArrayList<Customer> customerList = customerManager.getList() ;
		for(int i=0; i<customerList.size(); i++){
			String custName = customerList.get(i).getName() ;
			comboBoxModel.addElement(custName);
			comboBox.setModel(comboBoxModel);
		}
	}
	
	public void refreshCategoryData(){
		ArrayList<Product> products = newSale.getProductList();
		for(Product p : products){
			ArrayList<Category> categories = p.getCategoriesOfProduct();
			for(Category c :categories){
				c.addToTotalProductsSold();
				c.addToTotalRevenue(p.getPrice());
			}
			
		}
	}

	@Override
	public void focusGained(FocusEvent f) {
		if(f.getSource().equals(searchField)){
			searchField.setText("");
		}
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSearch)){
			ArrayList<Product> tempList = productManager.searchForProduct(searchField.getText().toUpperCase());
			fillTempTable(tempList);
		}
		else if(e.getSource().equals(btnAddToCart)){
			try{
				int selectedRow = tempTable.getSelectedRow();
				String selectedProductID = (String)tempTable.getValueAt(selectedRow, 0) ;
				Product prod = productManager.getProductWithID(selectedProductID);
				String userInput = JOptionPane.showInputDialog(this, "Enter quantity.", "", JOptionPane.OK_CANCEL_OPTION) ;
				
				if(userInput != null){
					int selectedQuantity = Integer.parseInt(userInput);
					if(selectedQuantity<=prod.getQuantity()){
						prod.setQuantity(prod.getQuantity()-selectedQuantity);
						addProductToFinalTable(prod,selectedQuantity);
						newSale.calculateTotalCost();
						txtTotalCost.setText(String.valueOf(newSale.getTotalCost()));
					}
					else{
						JOptionPane.showMessageDialog(this, "Not enough of this product in stock.", "", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(this, "No products are selected", "Error.", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
			
		}
		else if(e.getSource().equals(btnCommit)){
			if(!newSale.getProductList().isEmpty()){
				int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to proceed with this sale?", "", JOptionPane.YES_NO_OPTION);
				if(dialogResult==JOptionPane.YES_OPTION){
					Date date = new Date() ;
					newSale.setDate(date);
					Customer customer =(Customer)customerManager.getList().get(comboBox.getSelectedIndex());
					newSale.setCustomer(customer);
					salesManager.getList().add(newSale);
					customer.addPurchase(newSale);
					refreshCategoryData();//Ananewnei ta totalProductsSold kai  totalRevenue  tis kathe katigorias
					FileManager.saveToFile(MainFrame.SALES_FILE_NAME, salesManager);
					FileManager.saveToFile(MainFrame.PRODUCTS_FILE_NAME, productManager);
					createTempTable();
					createFinalTable();
					searchField.setText("ID/Description");
				}
				
			}
			else JOptionPane.showMessageDialog(this, "The cart is empty", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}


}
