import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;





public class ProductsIntFrame extends InternalFrame implements ActionListener ,MouseListener  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8531310730565484130L;
	/**
	 * 
	 */
	
	private ProductManager recordManager ;
	private ArrayList<Product> productList ;
	private JTable table;
	private ArrayList<JTextField> txtFields;
	private int rowOfSelectedProduct ;
	
	public ProductsIntFrame(ProductManager recordManager) {
		this.recordManager = recordManager ;
		productList = this.recordManager.getList() ;
		this.setTitle("Products");
		
		this.btnCreateNewRecord.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.btnSave.addActionListener(this);
		this.btnCreate.addActionListener(this);
		this.btnDelete.addActionListener(this);
		
		createTable() ;
		createNewProductForm();
		
	}
	
	public void createTable(){
			String col[] = {"ID","Description","Quantity","Price"};
			DefaultTableModel model = new DefaultTableModel(col,productList.size());
			table = new JTable(model){
		    	 @Override
		            public boolean isCellEditable(int row , int column) {
		                return false ;
		            }
		    };
		    table.addMouseListener(this);
		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    table.setToolTipText("Double click on a product to show details");
		    ToolTipManager.sharedInstance().setDismissDelay(2000);
			fillTable(ALL_RECORDS);
			
			
		}
	
	public void fillTable(String identifier){
		if(identifier.equals(ALL_RECORDS)){
			int j =0;
			for(Product p : productList){
				
		    	table.setValueAt(p.getId(),j,0);
		    	table.setValueAt(p.getDescription(), j, 1);
		    	table.setValueAt(p.getQuantity(),j,2);
		    	table.setValueAt(p.getPrice(),j,3);
		    	j++;
			}
			this.scrollPane.setViewportView(table);
			this.allRecordsPanel.repaint();
		}
				
		
		
	}
	
	
	
	public void createNewProductForm(){
		boxPanel.removeAll();
		String[] fieldNames = this.recordManager.getProductFieldNames();
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
	
	public void fillFormWith(Product p){
		txtFields.get(0).setText(String.valueOf(p.getId()));
		txtFields.get(1).setText(p.getDescription());
		txtFields.get(2).setText(String.valueOf(p.getQuantity()));
		txtFields.get(3).setText(Double.toString(p.getPrice()));
		txtFields.get(4).setText(p.getSupplier());
		txtFields.get(5).setText(Double.toString(p.getHeight()));
		txtFields.get(6).setText(p.getCategory());
		txtFields.get(7).setText(Double.toString(p.getWidth()));
	}
	
	public void saveToFile(){
		try{
			File outDir = new File(MainFrame.PRODUCTS);
			System.out.println("Attempting to save to file");
			if(!outDir.exists()){
				outDir.mkdir();
				System.out.println("Have just created an outDir");
			}
			FileOutputStream fos 
			= new FileOutputStream(outDir+
									File.separator+
									MainFrame.PRODUCTS_FILE_NAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			if(recordManager != null){
				oos.writeObject(recordManager);
				System.out.println("Attempted to save Product Manager");
			}
				
			oos.close();
			fos.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent m) {
		Point p = m.getPoint() ;
		rowOfSelectedProduct = table.rowAtPoint(p);
		 		if(m.getClickCount()==2){
		 			lblTopOfForm.setText("Edit or Delete this Product");
		 			btnSave.setVisible(true);
		 			btnDelete.setVisible(true);
		 			btnCreate.setVisible(false);
		 			fillFormWith(productList.get(rowOfSelectedProduct));
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
			lblTopOfForm.setText("Create a new Product");
			btnCreate.setVisible(true);
			btnSave.setVisible(false);
			btnDelete.setVisible(false);
			createNewProductForm();
			cardLayout.show(mainPanel, FORM_RECORD);
		}
		else if(e.getSource().equals(btnCancel)) {
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to leave this page?", "Warning!", JOptionPane.YES_NO_OPTION);	
			if(dialogueResult==JOptionPane.YES_OPTION){
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
		}
		else if (e.getSource().equals(btnCreate)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Save this Product?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				productList.add((new Product(txtFields.get(0).getText(),txtFields.get(1).getText(),
						txtFields.get(2).getText(),txtFields.get(3).getText(),
						txtFields.get(4).getText(),txtFields.get(5).getText(),
						txtFields.get(6).getText(),txtFields.get(7).getText())));
				createTable();
				saveToFile();
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
		}
		else if (e.getSource().equals(btnSave)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Save this Product?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				
			}
			createTable();
			cardLayout.show(mainPanel, ALL_RECORDS);
			
		}
		else if(e.getSource().equals(btnDelete)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this Product?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				productList.remove(rowOfSelectedProduct);
				createTable();
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
		}
		
	}
	
}
