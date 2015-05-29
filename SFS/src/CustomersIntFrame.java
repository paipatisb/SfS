import javax.swing.JInternalFrame;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class CustomersIntFrame extends JInternalFrame implements ActionListener,MouseListener {
	private static final String NEWCUSTOMERPANEL = "New Customer Panel";
	private static final String CUSTOMERSPANEL = "All Customers Panel";
	private static final String TABLEBASEPANEL = "The base panel for the table" ;
	private CustomerManager customerManager ;
	private JButton btnNewCustomer, btnOk ,btnCancel;
	private CustomersTablePanel customersTablePanel ;
	private JPanel mainCustomerPanel ,newCustomerPanel , tableBasePanel,allCustomersPanel;
	private CardLayout cardLayout = new CardLayout() , tableBasePanelCardLayout = new CardLayout();
	private JTextField txtName ,txtLastName , txtAddress , txtEmail ;
	private UIManager.LookAndFeelInfo[] looks ;
	private JTable table;
	private MouseListener mListener = this ;
	public CustomersIntFrame(JFrame mainFrame ,CustomerManager customerManager) {
		super("Customers");
		this.customerManager = customerManager ;
		setLookAndFeels() ;
		
		paintMainCustomerPanel();
		btnNewCustomer.addActionListener(this);
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
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
	
	private void paintMainCustomerPanel(){
		getContentPane().setLayout(new BorderLayout(0, 0));
		mainCustomerPanel = new JPanel(cardLayout);
		getContentPane().add(mainCustomerPanel);
		
		allCustomersPanel = new JPanel();//Αρχική σελίδα των "Customers".Έχει : buttonPanel , tableBasePanel 
		allCustomersPanel.setLayout(new BorderLayout(0, 0));
		JPanel buttonPanel = new JPanel();
		allCustomersPanel.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		btnNewCustomer = new JButton("New Customer");
		buttonPanel.add(btnNewCustomer);
		tableBasePanel = new JPanel(); //Βάση για το customerTablePanel
		allCustomersPanel.add(tableBasePanel, BorderLayout.CENTER);
		
		tableBasePanel.setLayout(tableBasePanelCardLayout);
		
		customersTablePanel = new CustomersTablePanel();
		tableBasePanel.add(customersTablePanel,TABLEBASEPANEL);
		
		newCustomerPanel = new NewCustomerPanel() ;
		
		mainCustomerPanel.add(newCustomerPanel,NEWCUSTOMERPANEL);
		mainCustomerPanel.add(allCustomersPanel,CUSTOMERSPANEL);
		cardLayout.show(mainCustomerPanel, CUSTOMERSPANEL);
		
	}
	
				
	
	
	
	
	//Class representing the JPanel which holds the Customer Table
	public class CustomersTablePanel extends JPanel{
		private ArrayList<Customer> customerList ;
		private DefaultTableModel model ;
		
		public CustomersTablePanel(){
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
		    table.addMouseListener(mListener);
		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
			txtName.setBounds(140, 42, 125, 23);
			add(txtName);
			txtName.setColumns(10);
			
			txtLastName = new JTextField();
			txtLastName.setBounds(140, 67, 125, 23);
			add(txtLastName);
			txtLastName.setColumns(10);
			
			txtAddress = new JTextField();
			txtAddress.setBounds(140, 92, 125, 23);
			add(txtAddress);
			txtAddress.setColumns(10);
			
			txtEmail = new JTextField();
			txtEmail.setBounds(140, 117, 125, 23);
			add(txtEmail);
			txtEmail.setColumns(10);
			
			btnOk = new JButton("Ok");
			btnOk.setBounds(30, 180, 89, 23);
			add(btnOk);
			
			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(140, 180, 89, 23);
			add(btnCancel);
			
			JLabel lblCreateNewCustomer = new JLabel("Create New Customer.");
			lblCreateNewCustomer.setBounds(30, 20, 152, 14);
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
				customersTablePanel.paintPanel();
				tableBasePanelCardLayout.show(tableBasePanel, TABLEBASEPANEL);
				cardLayout.show(mainCustomerPanel, CUSTOMERSPANEL);
			}
		}
		else if(e.getSource().equals(btnCancel)){
			txtName.setText("");txtLastName.setText("");txtAddress.setText("");txtEmail.setText("");
			cardLayout.show(mainCustomerPanel, CUSTOMERSPANEL);
		}
	}
	@Override
	public void mouseClicked(MouseEvent m) {
		Point p = m.getPoint() ;
		int row = table.rowAtPoint(p);
		if(m.getClickCount()==2){
			System.out.println("Molis ekanes double click stin grami tou pinaka:");
			System.out.println(row);
			
			
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
