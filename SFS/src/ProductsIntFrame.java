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
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

public class ProductsIntFrame extends C_P_InternalFrame implements ActionListener ,MouseListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8531310730565484130L;
	private ProductManager productManager ;
	private CategoryManager categoryManager ;
	private ArrayList<Product> productList ;
	private JTable table;
	private ArrayList<JTextField> txtFields;
	private int rowOfSelectedProduct ;
	private JButton btnCategories ,btnAddCategory , btnDeleteCategory ;
	private CategoriesPanel categoriesPanel ;
	private Product selectedProduct;
	private JList<String> catJList ;
	private JScrollPane scrollPane1 ;
	private JPanel panel;
	private DefaultListModel<String> listModel ;
	public ProductsIntFrame(ProductManager productManager,CategoryManager categoryManager) {
		
		this.productManager = productManager ;
		this.categoryManager =categoryManager ;
		productList =this.productManager.getList() ;
		this.setTitle("Products");
		btnCategories = new JButton("Product Categories");
		topButtonPalen.add(btnCategories);
		btnAddCategory = new JButton("Add Category");
		btnDeleteCategory = new JButton("Delete Category");
		
		scrollPane1=new JScrollPane();
		
		categoriesPanel = new CategoriesPanel(categoryManager,mainPanel,cardLayout);
		
		mainPanel.add(categoriesPanel, CATEGORY_PANEL);
		
		
		
		this.btnCreateNewRecord.addActionListener(this);
		this.btnCategories.addActionListener(this);
		btnAddCategory.addActionListener(this);
		btnDeleteCategory.addActionListener(this);
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
		    table.getTableHeader().setReorderingAllowed(false);
		    ToolTipManager.sharedInstance().setDismissDelay(2000);
			fillTable();
			this.scrollPane.setViewportView(table);
			this.allRecordsPanel.repaint();	
			
		}
	
	public void fillTable(){
			int j =0;
			for(Product p : productList){
				
		    	table.setValueAt(p.getId(),j,0);
		    	table.setValueAt(p.getDescription(), j, 1);
		    	table.setValueAt(p.getQuantity(),j,2);
		    	table.setValueAt(p.getPrice(),j,3);
		    	j++;
			}
	}
	
	
	
	public void createNewProductForm(){
		boxPanel.removeAll();
		String[] fieldNames = this.productManager.getProductFieldNames();
		txtFields = new ArrayList<JTextField>() ;
		Box lblBox = Box.createVerticalBox();
		Box txtBox = Box.createVerticalBox() ;
		for(int i=0; i<productManager.getFieldCount(); i++){
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
		JPanel categoriesPanel = new JPanel(new BorderLayout());
		categoriesPanel.add(new JLabel("Product Categories :"),BorderLayout.WEST);
		categoriesPanel.add(scrollPane1,BorderLayout.CENTER);
		JPanel botPanel = new JPanel();
		botPanel.add(btnAddCategory);
		botPanel.add(btnDeleteCategory);
		categoriesPanel.add(botPanel,BorderLayout.SOUTH);
		boxPanel.add(categoriesPanel,BorderLayout.SOUTH);
		
		listModel = new DefaultListModel<String>();
		catJList = new JList();
		catJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		scrollPane1.setViewportView(catJList);
		catJList.setModel(listModel);
	}
	
	public void fillFormWith(Product p){
		txtFields.get(0).setText(String.valueOf(p.getId()));
		txtFields.get(1).setText(p.getDescription());
		txtFields.get(2).setText(String.valueOf(p.getQuantity()));
		txtFields.get(3).setText(Double.toString(p.getPrice()));
		txtFields.get(4).setText(p.getSupplier());
		txtFields.get(5).setText(Double.toString(p.getHeight()));
		txtFields.get(6).setText(Double.toString(p.getWidth()));
		ArrayList<Category> categories = p.getCategoriesOfProduct();
		
		listModel.removeAllElements();
		for(Category c: categories){
			listModel.addElement(c.getCategoryName());
		}
		catJList.repaint();
		scrollPane1.repaint();
	}
	

	@Override
	public void mouseClicked(MouseEvent m) {
		if(m.getSource().equals(table)){
			Point p = m.getPoint() ;
			rowOfSelectedProduct = table.rowAtPoint(p);
			selectedProduct = productList.get(rowOfSelectedProduct) ;
		 		if(m.getClickCount()==2){
		 			lblTopOfForm.setText("Edit or Delete this Product");
		 			btnSave.setVisible(true);
		 			btnDelete.setVisible(true);
		 			btnCreate.setVisible(false);
		 			fillFormWith(selectedProduct);
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
				categoryManager.clearDummyList();
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
		}
		else if(e.getSource().equals(btnCategories)){
			
			cardLayout.show(mainPanel, CATEGORY_PANEL);
		}
		else if (e.getSource().equals(btnCreate)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Save this Product?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				try{
					Product prod=new Product(txtFields.get(0).getText().toUpperCase(),
											txtFields.get(1).getText().toUpperCase(),
											txtFields.get(2).getText(),
											txtFields.get(3).getText(),
											txtFields.get(4).getText().toUpperCase(),
											txtFields.get(5).getText(),
											txtFields.get(6).getText());
					
					for(int i=0; i<categoryManager.getDummyCategoryList().size(); i++){
						categoryManager.getDummyCategoryList().get(i).addProduct(prod);
						prod.addCategory(categoryManager.getDummyCategoryList().get(i));
					}
					productList.add(prod);
					categoryManager.clearDummyList();
					createTable();
					FileManager.saveToFile(MainFrame.PRODUCTS_FILE_NAME, productManager);
					FileManager.saveToFile(MainFrame.PRODUCT_CATEGORIES_FILE_NAME, categoryManager);
					cardLayout.show(mainPanel, ALL_RECORDS);
				}
				catch(Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Wrong input format", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(e.getSource().equals(btnAddCategory)){
			new CategoryInputFrame(categoryManager,listModel);
			
		}
		else if(e.getSource().equals(btnDeleteCategory)){
			
			if(catJList.getSelectedValue()!=null){
				int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this category?", "Warning!", JOptionPane.YES_NO_OPTION);
				if(dialogueResult==JOptionPane.YES_OPTION)
					listModel.removeElement(catJList.getSelectedValue());
			}
			else JOptionPane.showMessageDialog(this, "Select the category that you want to remove.", "Error.", JOptionPane.ERROR_MESSAGE);
			
		}
		else if (e.getSource().equals(btnSave)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Save this Product?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				selectedProduct.setId(txtFields.get(0).getText().toUpperCase());
				selectedProduct.setDescription(txtFields.get(1).getText().toUpperCase());
				selectedProduct.setQuantity(Integer.parseInt(txtFields.get(2).getText()));
				selectedProduct.setPrice(Double.parseDouble(txtFields.get(3).getText()));
				selectedProduct.setSupplier(txtFields.get(4).getText().toUpperCase());
				selectedProduct.setHeight(Double.parseDouble(txtFields.get(5).getText()));
				selectedProduct.setWidth(Double.parseDouble(txtFields.get(6).getText()));
				for(Category c: categoryManager.getDummyCategoryList()){
					selectedProduct.addCategory(c);
				}
				FileManager.saveToFile(MainFrame.PRODUCTS_FILE_NAME, productManager);
			}
			
			createTable();
			FileManager.saveToFile(MainFrame.PRODUCTS_FILE_NAME, productManager);
			FileManager.saveToFile(MainFrame.PRODUCT_CATEGORIES_FILE_NAME, categoryManager);
			cardLayout.show(mainPanel, ALL_RECORDS);
			
		}
		else if(e.getSource().equals(btnDelete)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this Product?", "Warning!", JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				productList.remove(rowOfSelectedProduct);
				createTable();
				FileManager.saveToFile(MainFrame.PRODUCTS_FILE_NAME, productManager);
				cardLayout.show(mainPanel, ALL_RECORDS);
			}
		}
		
	}
	
}
