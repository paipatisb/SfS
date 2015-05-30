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

import javax.swing.JInternalFrame;
import javax.swing.UIManager ;


public class MainFrame extends JFrame implements ActionListener {
	public static final String PRODUCTS_FILE_NAME ="products.ser";
	public static final String CUSTOMERS_FILE_NAME = "customers.ser";
	private static final String SAVE_FOLDER = "Save" ;
	public static final String PRODUCTS = "Products" ;
	public static final String CUSTOMERS = "Customers";
	private static final String SALES= "Sales";
	private static final String HISTORY = "History" ;
	private JButton customersButton,newSaleButton,productsButton,historyButton ;
	
	private CustomerManager customerManager =null ;
	private ProductManager  productManager  = null ; 
	
	private JInternalFrame customerFrame , productFrame ;
	private CardLayout cardLayout ;
	private JPanel right_panel,left_panel ;
	
	private UIManager.LookAndFeelInfo[] looks ;
	public MainFrame() {
		super("Solution For Sales.");
		
		customerManager = loadFromFile(CUSTOMERS) ;
		productManager = loadFromFile1();
		
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
		customersButton.addActionListener(this);
		btnSize = customersButton.getSize() ;
		left_panel.add(customersButton);
		productsButton = new JButton("Products");
		productsButton.addActionListener(this);
		productsButton.setSize(btnSize);
		left_panel.add(productsButton);
		newSaleButton = new JButton("New Sale");
		newSaleButton.setSize(btnSize);
		left_panel.add(newSaleButton);
		historyButton = new JButton("History");
		historyButton.setSize(btnSize);
		left_panel.add(historyButton);
		/*
		 * Create Frames for  right_Panel 
		 */
		right_panel = new JPanel();
		splitPane.setRightComponent(right_panel);
		
		cardLayout = new CardLayout() ;
		right_panel.setLayout(cardLayout);
		
		customerFrame = new CustomersIntFrame(customerManager);
		productFrame = new ProductsIntFrame(productManager) ;
		
		
		right_panel.add(customerFrame, CUSTOMERS);
		right_panel.add(productFrame, PRODUCTS);
		
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
	
	public CustomerManager loadFromFile(String identifier){
		try{
			FileInputStream fileIn = 
					new FileInputStream(SAVE_FOLDER+File.separator+PRODUCTS_FILE_NAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			productManager = (ProductManager)in.readObject();
			fileIn.close();
			in.close();
		}
		catch(Exception e){
			System.out.println("Cannot read file "+CUSTOMERS_FILE_NAME);
		}
		if(customerManager == null){
			customerManager = new CustomerManager();
			System.out.println("Just have created a Customer Manager");
		}
		return customerManager;
	}
	
	public ProductManager loadFromFile1(){
		try{
			FileInputStream fileIn = 
					new FileInputStream(SAVE_FOLDER+File.separator+PRODUCTS_FILE_NAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			productManager = (ProductManager)in.readObject();
			fileIn.close();
			in.close();
		}
		catch(Exception e){
			System.out.println("Cannot read file "+PRODUCTS_FILE_NAME);
		}
		if(productManager == null){
			productManager = new ProductManager();
			System.out.println("Just have created a Product Manager");
		}
		return productManager;
	}
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(customersButton)){
			cardLayout.show(right_panel, CUSTOMERS);
			
		}
		else if(e.getSource().equals(productsButton)){
			cardLayout.show(right_panel, PRODUCTS ) ;
			
		}
		
	}
}
