import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;



public class CustomersIntFrame extends C_P_InternalFrame implements ActionListener,MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7243167485344653516L;
	private CustomerManager customerManager ;
	private CategoryManager custCategoryManager ;
	private ArrayList<Customer> customerList;
	private JTable table;
	private ArrayList<JTextField> txtFields ;
	private int rowOfSelectedCustomer ;
	private Customer selectedCustomer ;
	public CustomersIntFrame(CustomerManager customerManager) {
		
		this.setTitle("Customers");
		this.customerManager = customerManager ;
		this.custCategoryManager = custCategoryManager ;
		this.customerList = customerManager.getList();
		
		this.btnCreateNewRecord.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.btnSave.addActionListener(this);
		this.btnCreate.addActionListener(this);
		this.btnDelete.addActionListener(this);
		
		createTable() ;
		createProductForm();
	}
	
	public void createTable(){
		String col[] = {"Name","Email","Phone Number"};
		DefaultTableModel model = new DefaultTableModel(col,customerList.size()); 
		table = new JTable(model){
	    	 @Override
	            public boolean isCellEditable(int row , int column) {
	                return false ;
	            }
	    };
	    table.addMouseListener(this);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setToolTipText("Double click on a customer to show details");
	    table.getTableHeader().setReorderingAllowed(false);
	    ToolTipManager.sharedInstance().setDismissDelay(2000);
		
		fillTable();
		this.scrollPane.setViewportView(table);
		this.allRecordsPanel.repaint();
	}
	
	public void fillTable(){
		int j =0;
		for(Customer c : customerList){
		   	table.setValueAt(c.getName(),j,0);
		   	table.setValueAt(c.getEmail(),j,1);
		   	table.setValueAt(c.getPhoneNumber(),j,2);
		   	
		   	j++;
		}
		
	}
	
	public void createProductForm(){
		boxPanel.removeAll();
		String[] fieldNames = customerManager.getCustomerFieldNames();
		txtFields = new ArrayList() ;
		Box lblBox = Box.createVerticalBox();
		Box txtBox = Box.createVerticalBox() ;
		for(int i=0; i<customerManager.getFieldCount(); i++){
			lblBox.add(Box.createVerticalStrut(30));
			lblBox.add(Box.createVerticalGlue());
			lblBox.add(new JLabel(fieldNames[i]+" :"));
			
			txtBox.add(Box.createVerticalStrut(20));
			txtBox.add(Box.createVerticalGlue());
			txtFields.add( new JTextField()) ;
			txtBox.add(txtFields.get(i));
			
		}
		
		lblBox.add(Box.createVerticalStrut(30));
		lblBox.add(Box.createVerticalGlue());
		txtBox.add(Box.createVerticalStrut(20));
		txtBox.add(Box.createVerticalGlue());
		boxPanel.add(lblBox,BorderLayout.WEST);
		boxPanel.add(txtBox,BorderLayout.CENTER);
	}		
	
	public void fillFormWith(Customer c){
		txtFields.get(0).setText(c.getName());
		txtFields.get(1).setText(c.getEmail());
		txtFields.get(2).setText(c.getPhoneNumber());
		txtFields.get(3).setText(c.getPhoneNumber2());
		txtFields.get(4).setText(c.getAddress());
		txtFields.get(5).setText(c.getAddress2());
		txtFields.get(6).setText(c.getAFM());
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent m) {
		Point p = m.getPoint() ;
		rowOfSelectedCustomer = table.rowAtPoint(p);
		if(rowOfSelectedCustomer!=0){
			selectedCustomer = customerList.get(rowOfSelectedCustomer) ;
			if(m.getClickCount()==2){
				lblTopOfForm.setText("Edit or Delete this Customer");
				btnSave.setVisible(true);
				btnDelete.setVisible(true);
				btnCreate.setVisible(false);
				fillFormWith(selectedCustomer);
				cardLayout.show(mainPanel, FORM_RECORD);
			}
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.btnCreateNewRecord)){
			lblTopOfForm.setText("Create New Customer");
			btnCreate.setVisible(true);
			btnSave.setVisible(false);
			btnDelete.setVisible(false);
			createProductForm();
			cardLayout.show(mainPanel, FORM_RECORD);
		}
		else if(e.getSource().equals(btnCancel)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to leave this page?", "Warning!", JOptionPane.YES_NO_OPTION);	
			if(dialogueResult==JOptionPane.YES_OPTION){
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
		}
		
		else if(e.getSource().equals(btnCreate)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Save this Customer?", "Warning!", JOptionPane.YES_NO_OPTION);	
			if(dialogueResult==JOptionPane.YES_OPTION){
				customerList.add((new Customer(txtFields.get(0).getText().toUpperCase(),
						txtFields.get(1).getText().toUpperCase(),txtFields.get(2).getText().toUpperCase(),
						txtFields.get(3).getText().toUpperCase(),txtFields.get(4).getText().toUpperCase(),
						txtFields.get(5).getText().toUpperCase(),txtFields.get(6).getText().toUpperCase())));
				createTable();
				FileManager.saveToFile(MainFrame.CUSTOMERS_FILE_NAME, customerManager);
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
		}
		else if(e.getSource().equals(btnSave)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Save the changes?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				selectedCustomer.setName(txtFields.get(0).getText().toUpperCase());
				selectedCustomer.setEmail(txtFields.get(1).getText().toUpperCase());
				selectedCustomer.setPhoneNumber(txtFields.get(2).getText());
				selectedCustomer.setPhoneNumber2(txtFields.get(3).getText());
				selectedCustomer.setAddress(txtFields.get(4).getText().toUpperCase());
				selectedCustomer.setAddress2(txtFields.get(5).getText().toUpperCase());
				selectedCustomer.setAFM(txtFields.get(6).getText());
				createTable();
			}
			
			cardLayout.show(mainPanel, ALL_RECORDS);
			
		}
		else if(e.getSource().equals(btnDelete)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this Customer?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				customerList.remove(rowOfSelectedCustomer);
				createTable();
				FileManager.saveToFile(MainFrame.CUSTOMERS_FILE_NAME, customerManager);
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
			
		}
		
		
		
		
	}
	
	
	
}
