import javax.swing.JInternalFrame;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class CustomersIntFrame extends JInternalFrame implements ActionListener {
	private static final String NEWCUSTOMERPANEL = "New Customer Panel";
	private static final String CUSTOMERSPANEL = "Customers Panel";
	private static final String TABLEBASEPANEL = "The base panel for the table" ;
	private CustomerManager customerManager ;
	private JButton btnNewCustomer, btnOk ,btnCancel;
	private JPanel tableBasePanel ;
	private CustomerTablePanel customerTablePanel ;
	private JPanel mainCustomerPanel ,newCustomerPanel ;
	private JPanel customersPanel;
	private CardLayout cardLayout = new CardLayout() , tableBasePanelCardLayout = new CardLayout();
	private JTextField txtName ,txtLastName , txtAddress , txtEmail ;
	
	
	
	public CustomersIntFrame(JFrame mainFrame ,CustomerManager customerManager) {
		super("Customers");
		this.customerManager = customerManager ;
		
		paintMainCustomerPanel();
		btnNewCustomer.addActionListener(this);
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
	}
	private void paintMainCustomerPanel(){
		getContentPane().setLayout(new BorderLayout(0, 0));
		mainCustomerPanel = new JPanel(cardLayout);
		getContentPane().add(mainCustomerPanel);
		
		customersPanel = new JPanel();//Αρχική σελίδα των "Customers".Έχει : buttonPanel , tableBasePanel 
		customersPanel.setLayout(new BorderLayout(0, 0));
		JPanel buttonPanel = new JPanel();
		customersPanel.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		btnNewCustomer = new JButton("New Customer");
		buttonPanel.add(btnNewCustomer);
		tableBasePanel = new JPanel(); //Βάση για το customerTablePanel
		customersPanel.add(tableBasePanel, BorderLayout.CENTER);
		tableBasePanel.setLayout(tableBasePanelCardLayout);
		customerTablePanel = new CustomerTablePanel();
		tableBasePanel.add(customerTablePanel,TABLEBASEPANEL);
		newCustomerPanel = new NewCustomerPanel() ;
		
		
		mainCustomerPanel.add(newCustomerPanel,NEWCUSTOMERPANEL);
		mainCustomerPanel.add(customersPanel,CUSTOMERSPANEL);
		cardLayout.show(mainCustomerPanel, CUSTOMERSPANEL);
		
	}
	
				
	
	
	
	
	//Class representing the JPanel which holds the Customer Table
	public class CustomerTablePanel extends JPanel{
		private JTable table;
		private ArrayList<Customer> customerList ;
		private DefaultTableModel model ;
		
		public CustomerTablePanel(){
			customerList = customerManager.getList() ;
			paintPanel();
		}
		public void paintPanel(){
			this.removeAll();
			this.fillTable();
			setLayout(new BorderLayout(0, 0));
			add(new JScrollPane(table));
		}
		
		public void fillTable(){
			String col[] = {"ID","Name","Last Name","Address","e-mail"};
			model = new DefaultTableModel(col,customerList.size()); 
			table = new JTable(model){
		    	 @Override
		            public boolean isCellEditable(int row , int column) {
		                return false ;
		            }
		    };
			int j =0;
			for(Customer c : customerList){
				
		    	table.setValueAt(j+1,j,0);
		    	table.setValueAt(c.getName(), j, 1);
		    	table.setValueAt(c.getLastName(),j,2);
		    	table.setValueAt(c.getAddress(),j,3);
		    	table.setValueAt(c.getEmail(),j,4);
		    	j++;
			}
		}
	}
	
	//Class representing the JPanel which holds the New Customer Form
	public class NewCustomerPanel extends JPanel {
		public NewCustomerPanel() {
			paintPanel();
		}
		public void paintPanel(){
			this.setBackground(Color.white);
			setLayout(null);
			JLabel lblName = new JLabel("Name :");
			lblName.setBounds(30, 45, 75, 14);
			add(lblName);
			
			JLabel lblLastName = new JLabel("Last Name :");
			lblLastName.setBounds(30, 70, 75, 14);
			add(lblLastName);
			
			JLabel lblAddress = new JLabel("Address :");
			lblAddress.setBounds(30, 95, 75, 14);
			add(lblAddress);
			
			JLabel lblEmail = new JLabel("E-mail :");
			lblEmail.setBounds(30, 120, 75, 14);
			add(lblEmail);
			
			txtName = new JTextField();
			txtName.setBounds(140, 42, 125, 20);
			add(txtName);
			txtName.setColumns(10);
			
			txtLastName = new JTextField();
			txtLastName.setBounds(140, 67, 125, 20);
			add(txtLastName);
			txtLastName.setColumns(10);
			
			txtAddress = new JTextField();
			txtAddress.setBounds(140, 92, 125, 20);
			add(txtAddress);
			txtAddress.setColumns(10);
			
			txtEmail = new JTextField();
			txtEmail.setBounds(140, 117, 125, 20);
			add(txtEmail);
			txtEmail.setColumns(10);
			
			btnOk = new JButton("Ok");
			btnOk.setBounds(30, 180, 89, 23);
			add(btnOk);
			
			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(140, 180, 89, 23);
			add(btnCancel);
			
			JLabel lblCreateNewCustomer = new JLabel("Create New Customer.");
			lblCreateNewCustomer.setBounds(50, 20, 152, 14);
			add(lblCreateNewCustomer);
		}

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnNewCustomer)){
			cardLayout.show(mainCustomerPanel, NEWCUSTOMERPANEL);
		}
		else if(e.getSource().equals(btnOk)){
			int dialogResult = JOptionPane.showConfirmDialog (this, "Create this new Customer?","Warning",JOptionPane.YES_NO_OPTION);
			if(dialogResult==JOptionPane.YES_OPTION){
				customerManager.addCustomer(new Customer(
						txtName.getText().toUpperCase(),
						txtLastName.getText().toUpperCase(),
						txtAddress.getText().toUpperCase(),
						txtEmail.getText()));
				txtName.setText("");txtLastName.setText("");txtAddress.setText("");txtEmail.setText("");
				customerTablePanel.paintPanel();
				tableBasePanelCardLayout.show(tableBasePanel, TABLEBASEPANEL);
				cardLayout.show(mainCustomerPanel, CUSTOMERSPANEL);
			}
		}
		else if(e.getSource().equals(btnCancel)){
			txtName.setText("");txtLastName.setText("");txtAddress.setText("");txtEmail.setText("");
			cardLayout.show(mainCustomerPanel, CUSTOMERSPANEL);
		}
	}
}
