import javax.swing.JInternalFrame;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.Box;
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
	private static final String CUSTOMERFORMPANEL = "Customer Form";
	private static final String CUSTOMERSPANEL = "All Customers Panel";
	private static final String TABLEBASEPANEL = "The base panel for the table" ;
	private CustomerManager customerManager ;
	private JButton btnNewCustomer, btnSave ,btnCancel ,btnDelete;
	private CustomersTablePanel customersTablePanel ;
	private CustomerFormPanel customerFormPanel ;
	private JPanel mainCustomerPanel  , tableBasePanel,allCustomersPanel;
	private CardLayout cardLayout = new CardLayout() , tableBasePanelCardLayout = new CardLayout();
	private JTextField txtName ,txtLastName , txtAddress , txtEmail , txtAddress2, txtPhoneNumber,txtPhoneNumber2 , txtAFM;
	private UIManager.LookAndFeelInfo[] looks ;
	private JTable table;
	private MouseListener mListener = this ;
	private JLabel lblCustomerForm ;
	private int rowOfSelectedCustomer  ;
	public CustomersIntFrame(JFrame mainFrame ,CustomerManager customerManager) {
		super("Customers");
		this.customerManager = customerManager ;
		setLookAndFeels() ;
		
		paintMainCustomerPanel();
		btnNewCustomer.addActionListener(this);
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);
		
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
		
		customerFormPanel = new CustomerFormPanel() ;
		
		mainCustomerPanel.add(customerFormPanel,CUSTOMERFORMPANEL);
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
	public class CustomerFormPanel extends JPanel {
		public CustomerFormPanel() {
			paintPanel();
		}
		public void paintPanel(){
			setLayout(new BorderLayout(0, 0));
			JPanel panel = new JPanel();
			add(panel, BorderLayout.NORTH);
			lblCustomerForm = new JLabel("");
			panel.add(lblCustomerForm);
			JPanel boxesPanel = new JPanel();
			add(new JScrollPane(boxesPanel), BorderLayout.CENTER);
			boxesPanel.setLayout(new BorderLayout(0, 0));
			Box lblBox = Box.createVerticalBox();
			boxesPanel.add(lblBox, BorderLayout.WEST);
			/*
			 * lblBox Components
			 */
			JLabel lblName = new JLabel("Name :");
			lblBox.add(lblName);
			lblName.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblName.setPreferredSize(new Dimension(80, 17));
			lblBox.add(Box.createVerticalGlue());
			lblBox.add(Box.createVerticalStrut(25));
			JLabel lblLastName = new JLabel("Last Name :");
			lblBox.add(lblLastName);
			lblLastName.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblLastName.setPreferredSize(new Dimension(80, 17));
			lblBox.add(Box.createVerticalGlue());
			lblBox.add(Box.createVerticalStrut(25));
			JLabel lblEmail = new JLabel("E-mail :");
			lblBox.add(lblEmail);
			lblEmail.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblEmail.setPreferredSize(new Dimension(80, 17));
			lblBox.add(Box.createVerticalGlue());
			lblBox.add(Box.createVerticalStrut(25));
			JLabel lblAddress = new JLabel("Address :");
			lblBox.add(lblAddress);
			lblAddress.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblAddress.setPreferredSize(new Dimension(80, 17));
			lblBox.add(Box.createVerticalGlue());
			lblBox.add(Box.createVerticalStrut(25));
			JLabel lblAddress2 = new JLabel("Address 2 :");
			lblBox.add(lblAddress2);
			lblAddress2.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblAddress2.setPreferredSize(new Dimension(80, 17));
			lblBox.add(Box.createVerticalGlue());
			lblBox.add(Box.createVerticalStrut(25));
			JLabel lblPhoneNumber = new JLabel("Phone Number :");
			lblBox.add(lblPhoneNumber);
			lblPhoneNumber.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblPhoneNumber.setPreferredSize(new Dimension(80, 17));
			lblBox.add(Box.createVerticalGlue());
			lblBox.add(Box.createVerticalStrut(25));
			JLabel lblPhoneNumber2 = new JLabel("Phone Number 2 :");
			lblBox.add(lblPhoneNumber2);
			lblPhoneNumber2.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblPhoneNumber2.setPreferredSize(new Dimension(80, 17));
			lblBox.add(Box.createVerticalGlue());
			lblBox.add(Box.createVerticalStrut(25));
			JLabel lblAFM = new JLabel("AFM :");
			lblBox.add(lblAFM);
			lblAFM.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblAFM.setPreferredSize(new Dimension(80, 17));
			lblBox.add(Box.createVerticalGlue());
			lblBox.add(Box.createVerticalStrut(25));
			/*
			 * End of lblBox Components
			 */
			Box txtBox = Box.createVerticalBox();
			boxesPanel.add(txtBox, BorderLayout.CENTER);
			/*
			 * txtBox Components
			 */
			txtName = new JTextField();
			txtBox.add(txtName);
			txtName.setColumns(10);
			txtName.setPreferredSize(new Dimension(50, 20));
			txtName.setMinimumSize(new Dimension(40,20));
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(Box.createVerticalStrut(20));
			txtLastName = new JTextField();
			txtBox.add(txtLastName);
			txtLastName.setColumns(10);
			txtLastName.setPreferredSize(new Dimension(50, 20));
			txtLastName.setMinimumSize(new Dimension(40,20));
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(Box.createVerticalStrut(20));
			txtEmail = new JTextField();
			txtBox.add(txtEmail);
			txtEmail.setColumns(10);
			txtEmail.setPreferredSize(new Dimension(50, 20));
			txtEmail.setMinimumSize(new Dimension(40,20));
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(Box.createVerticalStrut(20));
			txtAddress = new JTextField();
			txtBox.add(txtAddress);
			txtAddress.setColumns(10);
			txtAddress.setPreferredSize(new Dimension(50, 20));
			txtAddress.setMinimumSize(new Dimension(40,20));
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(Box.createVerticalStrut(20));
			txtAddress2 = new JTextField();
			txtBox.add(txtAddress2);
			txtAddress2.setColumns(10);
			txtAddress2.setPreferredSize(new Dimension(50, 20));
			txtAddress2.setMinimumSize(new Dimension(40,20));
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(Box.createVerticalStrut(20));
			txtPhoneNumber = new JTextField();
			txtBox.add(txtPhoneNumber);
			txtPhoneNumber.setColumns(10);
			txtPhoneNumber.setPreferredSize(new Dimension(50, 20));
			txtPhoneNumber.setMinimumSize(new Dimension(40,20));
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(Box.createVerticalStrut(20));
			txtPhoneNumber2 = new JTextField();
			txtBox.add(txtPhoneNumber2);
			txtPhoneNumber2.setColumns(10);
			txtPhoneNumber2.setPreferredSize(new Dimension(50, 20));
			txtPhoneNumber2.setMinimumSize(new Dimension(40,20));
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(Box.createVerticalStrut(20));
			txtAFM = new JTextField();
			txtBox.add(txtAFM);
			txtAFM.setColumns(10);
			txtAFM.setPreferredSize(new Dimension(50, 20));
			txtAFM.setMinimumSize(new Dimension(40,20));
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(Box.createVerticalStrut(20));
			/*
			 * Endo of txtBox Components
			 */
			Box buttonBox = Box.createHorizontalBox();
			add(buttonBox, BorderLayout.SOUTH);
			/*
			 * buttonBox Components
			 */
			btnSave = new JButton("Save");
			btnCancel = new JButton("Cancel");
			btnDelete = new JButton("Delete");
			buttonBox.add(btnSave);
			buttonBox.add(btnCancel);
			buttonBox.add(btnDelete);
		}
		
		public void fillFormOfCustomer(Customer c){
			txtName.setText(c.getName());
			txtLastName.setText(c.getLastName());
			txtEmail.setText(c.getEmail());
			txtAddress.setText(c.getAddress());
			txtAddress2.setText(c.getAddress2());
			txtPhoneNumber.setText(c.getPhoneNumber());
			txtPhoneNumber2.setText(c.getPhoneNumber2());
			txtAFM.setText(c.getAFM());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnNewCustomer)){
			btnDelete.show(false);
			cardLayout.show(mainCustomerPanel, CUSTOMERFORMPANEL);
		}
		else if(e.getSource().equals(btnSave)){
			int dialogResult = JOptionPane.showConfirmDialog (this, "Save Customer?","Warning",JOptionPane.YES_NO_OPTION);
			if(dialogResult==JOptionPane.YES_OPTION){
				customerManager.addCustomer(new Customer(
						txtName.getText().toUpperCase(),
						txtLastName.getText().toUpperCase(),
						txtAddress.getText().toUpperCase(),
						txtEmail.getText()));
				tableBasePanelCardLayout.show(tableBasePanel, TABLEBASEPANEL);
				cardLayout.show(mainCustomerPanel, CUSTOMERSPANEL);
			}
		}
		else if(e.getSource().equals(btnCancel)){
			int dialogueResult = JOptionPane.showConfirmDialog(this,"Leave This Customer Form?","Warning",JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				cardLayout.show(mainCustomerPanel, CUSTOMERSPANEL);
			}
			
		}
		else if(e.getSource().equals(btnDelete)){
			int dialogueResult = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this Customer?","Warning",JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				customerManager.deleteCustomerAtIndex(rowOfSelectedCustomer);
				cardLayout.show(mainCustomerPanel, CUSTOMERSPANEL);
			}
		}
		txtName.setText("");txtLastName.setText("");txtAddress.setText("");txtEmail.setText("");
		customersTablePanel.paintPanel();
	}
	@Override
	public void mouseClicked(MouseEvent m) {
		Point p = m.getPoint() ;
		if(m.getClickCount()==2){
			rowOfSelectedCustomer = table.rowAtPoint(p);
			btnDelete.show(true);
			lblCustomerForm.setText("Alter or Delete this Customer");
			customerFormPanel.fillFormOfCustomer(customerManager.getCustomerAtIndex(rowOfSelectedCustomer));
			cardLayout.show(mainCustomerPanel, CUSTOMERFORMPANEL);
			
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
