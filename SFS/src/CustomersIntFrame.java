import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;



public class CustomersIntFrame extends InternalFrame implements ActionListener,MouseListener {
	private CustomerManager recordManager ;
	private ArrayList<Customer> customerList;
	private JTable table;
	private ArrayList<JTextField> txtFields ;
	private int rowOfSelectedCustomer ;
	public CustomersIntFrame(CustomerManager recordManager) {

		this.setTitle("Customers");
		this.recordManager = recordManager ;
		this.customerList = recordManager.getList();
		
		this.btnCreateNewRecord.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.btnSave.addActionListener(this);
		this.btnCreate.addActionListener(this);
		this.btnDelete.addActionListener(this);
		
		createTable() ;
		createProductForm();
	}
	
	public void createTable(){
		String col[] = {"Name","Last Name","Email","Phone Number"};
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
	    ToolTipManager.sharedInstance().setDismissDelay(2000);
		
		fillTable();
	}
	
	public void fillTable(){
		int j =0;
		for(Customer c : customerList){
		   	table.setValueAt(c.getName(),j,0);
		   	table.setValueAt(c.getLastName(), j, 1);
		   	table.setValueAt(c.getEmail(),j,2);
		   	table.setValueAt(c.getPhoneNumber(),j,3);
		   	j++;
		}
		this.scrollPane.setViewportView(table);
		this.allRecordsPanel.repaint();
		
	}
	
	
	public void createProductForm(){
		boxPanel.removeAll();
		String[] fieldNames = this.recordManager.getCustomerFieldNames();
		txtFields = new ArrayList() ;
		Box lblBox = Box.createVerticalBox();
		Box txtBox = Box.createVerticalBox() ;
		for(int i=0; i<recordManager.getFieldCount(); i++){
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
		txtFields.get(1).setText(c.getLastName());
		txtFields.get(2).setText(c.getEmail());
		txtFields.get(3).setText(c.getPhoneNumber());
		txtFields.get(4).setText(c.getPhoneNumber2());
		txtFields.get(5).setText(c.getAddress());
		txtFields.get(6).setText(c.getAddress2());
		txtFields.get(7).setText(c.getAFM());
		
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		Point p = m.getPoint() ;
		rowOfSelectedCustomer = table.rowAtPoint(p);
		 		if(m.getClickCount()==2){
		 			lblTopOfForm.setText("Edit or Delete this Customer");
		 			btnSave.setVisible(true);
		 			btnDelete.setVisible(true);
		 			btnCreate.setVisible(false);
		 			fillFormWith(customerList.get(rowOfSelectedCustomer));
		 			cardLayout.show(mainPanel, FORM_RECORD);
		 			
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
				customerList.add((new Customer(txtFields.get(0).getText(),txtFields.get(1).getText(),
						txtFields.get(2).getText(),txtFields.get(3).getText(),
						txtFields.get(4).getText(),txtFields.get(5).getText(),
						txtFields.get(6).getText(),txtFields.get(7).getText())));
				createTable();
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
		}
		else if(e.getSource().equals(btnSave)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Save this Customer?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				
			}
			createTable();
			cardLayout.show(mainPanel, ALL_RECORDS);
			
		}
		else if(e.getSource().equals(btnDelete)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this Customer?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				customerList.remove(rowOfSelectedCustomer);
				createTable();
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
			
		}
		
		
		
		
	}
	
	
	
}
