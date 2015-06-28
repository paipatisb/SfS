import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.UIManager ;
import javax.swing.SwingConstants;
import javax.swing.JLabel;


public class MainFrame extends JFrame implements ActionListener {
	public static final String PRODUCTS_FILE_NAME ="products.ser";
	public static final String CUSTOMERS_FILE_NAME = "customers.ser";
	public static final String PRODUCT_CATEGORIES_FILE_NAME ="productCats.ser";
	public static final String SALES_FILE_NAME="sales.ser";
	public static final String SAVE_FOLDER = "Saves" ;
	public static final String PRODUCTS = "Products" ;
	public static final String CUSTOMERS = "Customers";
	private static final String NEWSALE= "New Sale";
	private static final String HISTORY = "History" ;
	private JButton customersButton,newSaleButton,productsButton,historyButton ;
	
	private CustomerManager customerManager =null ;
	private ProductManager  productManager  = null ; 
	private SalesManager 	salesManager 	= null ;
	private CategoryManager prodCategoryManager = null ;
	
	private JInternalFrame customerFrame , productFrame  ;
	private NewSaleIntFrame newSaleFrame;
	private HistoryIntFrame historyFrame;
	
	private CardLayout cardLayout ;
	private JPanel right_panel,left_panel ;
	
	private UIManager.LookAndFeelInfo[] looks ;
	private JButton btnNewButton;
	private JPanel panel;
	private JLabel lblDate;
	public MainFrame() {
		super("Solution For Sales.");
		
		customerManager = (CustomerManager)FileManager.loadFromFile(MainFrame.CUSTOMERS_FILE_NAME) ;
		productManager =(ProductManager)FileManager.loadFromFile(MainFrame.PRODUCTS_FILE_NAME);
		salesManager=(SalesManager)FileManager.loadFromFile(MainFrame.SALES_FILE_NAME);
		prodCategoryManager = (CategoryManager)FileManager.loadFromFile(MainFrame.PRODUCT_CATEGORIES_FILE_NAME);
		
		paintMainFrame();
		
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}
	
	public void paintMainFrame(){
		setLookAndFeels();
		Dimension btnSize = new Dimension();
		getContentPane().setLayout(new BorderLayout(0, 0));
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane);
		splitPane.setEnabled(false);
		/*
		 * Paint left_Panel 
		 */
		left_panel = new JPanel();
		splitPane.setLeftComponent(left_panel);
		left_panel.setLayout(new BoxLayout(left_panel, BoxLayout.Y_AXIS));
		customersButton = new JButton("Customers");
		customersButton.setMaximumSize(new Dimension(2147483647, 2147483647));
		customersButton.addActionListener(this);
		btnSize = customersButton.getPreferredSize();
		left_panel.add(customersButton);
		productsButton = new JButton("Products");
		productsButton.setMaximumSize(new Dimension(2147483647, 2147483647));
		productsButton.addActionListener(this);
		productsButton.setPreferredSize(btnSize);
		left_panel.add(productsButton);
		newSaleButton = new JButton("New Sale");
		newSaleButton.setMaximumSize(new Dimension(2147483647, 2147483647));
		newSaleButton.setPreferredSize(btnSize);
		newSaleButton.addActionListener(this);
		left_panel.add(newSaleButton);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setMaximumSize(new Dimension(2147483647, 2147483647));
		left_panel.add(btnNewButton);
		historyButton = new JButton("History");
		historyButton.setMaximumSize(new Dimension(2147483647, 2147483647));
		historyButton.setPreferredSize(btnSize);
		historyButton.addActionListener(this);
		left_panel.add(historyButton);
		/*
		 * Create Frames for  right_Panel 
		 */
		right_panel = new JPanel();
		splitPane.setRightComponent(right_panel);
		
		cardLayout = new CardLayout() ;
		right_panel.setLayout(cardLayout);
		
		
		customerFrame = new CustomersIntFrame(customerManager);
		productFrame = new ProductsIntFrame(productManager,prodCategoryManager) ;
		newSaleFrame = new NewSaleIntFrame(productManager,customerManager,salesManager);
		historyFrame = new HistoryIntFrame(salesManager);
		
		right_panel.add(customerFrame, CUSTOMERS);
		right_panel.add(productFrame, PRODUCTS);
		right_panel.add(newSaleFrame , NEWSALE );
		right_panel.add(historyFrame,HISTORY);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		lblDate = new JLabel();
		Date date = new Date() ;
		lblDate.setText(date.toString());
		panel.add(lblDate);
		
	}
	
	public void setLookAndFeels(){

		looks = UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(looks[1].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(customersButton)){
			customerFrame.repaint();
			cardLayout.show(right_panel, CUSTOMERS);
			
		}
		else if(e.getSource().equals(productsButton)){
			productFrame.repaint();
			
			cardLayout.show(right_panel, PRODUCTS ) ;
		}
		else if(e.getSource().equals(newSaleButton)){
			newSaleFrame.initiateComponents();
			cardLayout.show(right_panel, NEWSALE);
		}
		else if(e.getSource().equals(historyButton)){
			historyFrame.fillTable();
			cardLayout.show(right_panel, HISTORY);
		}
		
	}
}
