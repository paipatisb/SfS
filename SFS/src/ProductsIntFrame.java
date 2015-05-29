import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;






public class ProductsIntFrame extends JInternalFrame implements ActionListener, MouseListener {
	private static final String PRODUCTFORMPANEL = "Product Form ";
	private static final String PRODUCTSPANEL = "All Products Panel";
	private static final String TABLEBASEPANEL = "The base panel for the table" ;
	private JPanel mainProductsPanel  , tableBasePanel,allProductsPanel;
	private JPanel buttonPanel;
	private JButton btnNewProduct;
	private ProductsTablePanel productsTablePanel ;
	private ProductFormPanel productFormPanel ;
	private ProductManager productManager ;
	private CardLayout cardLayout = new CardLayout() , tableBasePanelCardLayout = new CardLayout();
	private JTextField txtDescr , txtPrice ,txtQuantity , txtID ,txtHeight ,txtWeight ,txtWidth ;
	private JButton btnSave,btnCreate ,btnCancel , btnDelete ;
	private JTable table;
	private MouseListener mListener = this ;
	private JLabel lblProductForm ;
	private int rowOfSelectedProduct ;
	public ProductsIntFrame(JFrame mainFrame,ProductManager productManager) {
		super("Products");
		this.productManager = productManager;
		paintMainProductsPanel() ;
		
		btnNewProduct.addActionListener(this);
		btnSave.addActionListener(this);
		btnCreate.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);
		
	}
	/*
	 * 
	 */
	public void paintMainProductsPanel(){
		
		mainProductsPanel = new JPanel();
		getContentPane().add(mainProductsPanel, BorderLayout.CENTER);
		mainProductsPanel.setLayout(cardLayout);
		allProductsPanel = new JPanel();
		mainProductsPanel.add(allProductsPanel);
		allProductsPanel.setLayout(new BorderLayout(0, 0));
		
		buttonPanel = new JPanel();
		allProductsPanel.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		btnNewProduct = new JButton("New Product");
		buttonPanel.add(btnNewProduct);
		
		tableBasePanel = new JPanel();
		allProductsPanel.add(tableBasePanel, BorderLayout.CENTER);
		
		tableBasePanel.setLayout(tableBasePanelCardLayout);
		
		productsTablePanel = new ProductsTablePanel();
		tableBasePanel.add(productsTablePanel,TABLEBASEPANEL);
		
		productFormPanel = new ProductFormPanel() ;
		mainProductsPanel.add(productFormPanel,PRODUCTFORMPANEL);
		mainProductsPanel.add(allProductsPanel,PRODUCTSPANEL);
		cardLayout.show(mainProductsPanel, PRODUCTSPANEL);
		
	}
	/*
	 * 
	 */
	public class ProductsTablePanel extends JPanel {
		private ArrayList<Product> productList ;
		private DefaultTableModel model ;
		
		public ProductsTablePanel(){
			productList = productManager.getList() ;
			paintPanel();
		}
		/*
		 * Paints the panel that holds the product table
		 */
		public void paintPanel(){
			this.removeAll();
			this.fillTable();
			setLayout(new BorderLayout(0, 0));
			add(new JScrollPane(table));
		}
		
		/*
		 * Fills in the table with product info
		 */
		public void fillTable(){
			String col[] = {"ID","Description","Quantity","Price"};
			model = new DefaultTableModel(col,productList.size()); 
			table = new JTable(model){
		    	 @Override
		            public boolean isCellEditable(int row , int column) {
		                return false ;
		            }
		    };
		    table.addMouseListener(mListener);
		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    table.setToolTipText("Double click on a product to show details");
		    ToolTipManager.sharedInstance().setDismissDelay(2500);
			int j =0;
			for(Product p : productList){
				
		    	table.setValueAt(p.getId(),j,0);
		    	table.setValueAt(p.getDescription(), j, 1);
		    	table.setValueAt(p.getQuantity(),j,2);
		    	table.setValueAt(p.getPrice(),j,3);
		    	j++;
			}
		}
	}
	/*
	 * 
	 */
	public class ProductFormPanel extends JPanel{
		
		
		public ProductFormPanel(){
			paintPanel();
		}
		/*
		 * 
		 */
		public void paintPanel(){
			setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			add(panel, BorderLayout.NORTH);
			
			lblProductForm = new JLabel();
			panel.add(lblProductForm);
			
			JPanel boxesPanel = new JPanel();
			add(new JScrollPane(boxesPanel), BorderLayout.CENTER);
			boxesPanel.setLayout(new BorderLayout(0, 0));
			
			Box lblBox = Box.createVerticalBox();
			boxesPanel.add(lblBox, BorderLayout.WEST);
			/*
			 * lblBox Components
			 */
			JLabel lblID = new JLabel("ID :");
			lblID.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblID.setPreferredSize(new Dimension(80, 17));
			lblBox.add(lblID);
			lblBox.add(Box.createVerticalStrut(25));
			lblBox.add(Box.createVerticalGlue());
			JLabel lbldescr = new JLabel("Description :");
			lbldescr.setMaximumSize(new Dimension(2147483647, 2147483647));
			lbldescr.setPreferredSize(new Dimension(80, 17));
			lblBox.add(lbldescr);
			lblBox.add(Box.createVerticalStrut(25));
			lblBox.add(Box.createVerticalGlue());
			JLabel lblquantity = new JLabel("Quantity :");
			lblquantity.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblquantity.setPreferredSize(new Dimension(80, 17));
			lblBox.add(lblquantity);
			lblBox.add(Box.createVerticalStrut(25));
			lblBox.add(Box.createVerticalGlue());
			JLabel lblprice = new JLabel("Price :");
			lblprice.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblprice.setPreferredSize(new Dimension(80, 17));
			lblBox.add(lblprice);
			lblBox.add(Box.createVerticalStrut(25));
			lblBox.add(Box.createVerticalGlue());
			JLabel lblHeight = new JLabel("Height :");
			lblHeight.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblHeight.setPreferredSize(new Dimension(80, 17));
			lblBox.add(lblHeight);
			lblBox.add(Box.createVerticalStrut(25));
			lblBox.add(Box.createVerticalGlue());
			JLabel lblWidth = new JLabel("Width :");
			lblWidth.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblWidth.setPreferredSize(new Dimension(80, 17));
			lblBox.add(lblWidth);
			lblBox.add(Box.createVerticalStrut(25));
			lblBox.add(Box.createVerticalGlue());
			JLabel lblWeight = new JLabel("Weight :");
			lblWeight.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblWeight.setPreferredSize(new Dimension(80, 17));
			lblBox.add(lblWeight);
			lblBox.add(Box.createVerticalStrut(25));
			lblBox.add(Box.createVerticalGlue());
			/*
			 * End of lblbox Components
			 */
			Box txtBox = Box.createVerticalBox();
			boxesPanel.add(txtBox, BorderLayout.CENTER);
			/*
			 * txt Box Components
			 */
			txtID = new JTextField();
			txtID.setPreferredSize(new Dimension(6, 20));
			txtBox.add(txtID);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			txtDescr = new JTextField();
			txtDescr.setPreferredSize(new Dimension(6, 20));
			txtBox.add(txtDescr);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			txtQuantity = new JTextField();
			txtQuantity.setPreferredSize(new Dimension(6, 20));
			txtBox.add(txtQuantity);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			txtPrice = new JTextField();
			txtPrice.setPreferredSize(new Dimension(6, 20));
			txtBox.add(txtPrice);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			txtHeight = new JTextField();
			txtHeight.setPreferredSize(new Dimension(6, 20));
			txtBox.add(txtHeight);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			txtWidth = new JTextField();
			txtWidth.setPreferredSize(new Dimension(6, 20));
			txtBox.add(txtWidth);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			txtWeight = new JTextField();
			txtWeight.setPreferredSize(new Dimension(6, 20));
			txtBox.add(txtWeight);
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(Box.createVerticalStrut(25));
			/*
			 * End of txtBox Components
			 */
			Box buttonBox = Box.createHorizontalBox();
			add(buttonBox, BorderLayout.SOUTH);
			/*
			 * buttonBox Components
			 */
			
			btnSave = new JButton("Save");
			btnCreate = new JButton("Create");
			btnCancel = new JButton("Cancel");
			btnDelete = new JButton("Delete");
			buttonBox.add(btnSave);
			buttonBox.add(btnCreate);
			buttonBox.add(btnCancel);
			buttonBox.add(btnDelete);
		}
		
		public void fillFormOfPoduct(Product p){
			txtID.setText(Integer.toString(p.getId()));
			txtDescr.setText(p.getDescription());
			txtQuantity.setText(Integer.toString(p.getQuantity()));
			txtPrice.setText(Double.toString(p.getPrice()));
			txtHeight.setText(Double.toString(p.getHeight()));
			txtWeight.setText(Double.toString(p.getWeight()));
			txtWidth.setText(Double.toString(p.getWidth()));
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnNewProduct)){
			cardLayout.show(mainProductsPanel, PRODUCTFORMPANEL);
			lblProductForm.setText("Create New Product");
			btnDelete.show(false);
			btnSave.show(false);
		}
		else if(e.getSource().equals(btnCreate)){
			int dialogResult = JOptionPane.showConfirmDialog (this, "Save Product?","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult==JOptionPane.YES_OPTION){
				productManager.addProduct(new Product(
						Integer.parseInt(txtID.getText()),
						txtDescr.getText().toUpperCase(),
						Integer.parseInt(txtQuantity.getText()),
						Double.parseDouble(txtPrice.getText())));
				tableBasePanelCardLayout.show(tableBasePanel, TABLEBASEPANEL);
				cardLayout.show(mainProductsPanel, PRODUCTSPANEL);
			}
		}
		else if(e.getSource().equals(btnSave)){
			int dialogResult = JOptionPane.showConfirmDialog (this, "Create this new Product?","Warning",JOptionPane.YES_NO_OPTION);
			if(dialogResult==JOptionPane.YES_OPTION){
				Product p = productManager.getProductAtIndex(rowOfSelectedProduct);
				p.setDescription(txtDescr.getText());
				p.setQuantity(Integer.parseInt(txtQuantity.getText()));
				p.setPrice(Double.parseDouble(txtPrice.getText()));
				p.setHeight(Double.parseDouble(txtHeight.getText()));
				p.setWeight(Double.parseDouble(txtWeight.getText()));
				p.setWidth(Double.parseDouble(txtWidth.getText()));
				cardLayout.show(mainProductsPanel, PRODUCTSPANEL);
			}
		}
		else if(e.getSource().equals(btnCancel)){
			int dialogueResult = JOptionPane.showConfirmDialog(this,"Leave this Product form?","Warning",JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				cardLayout.show(mainProductsPanel, PRODUCTSPANEL);
			}
			
		}
		else if(e.getSource().equals(btnDelete)){
			int dialogueResult = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this Product?","Warning",JOptionPane.YES_NO_OPTION);
			if(dialogueResult==JOptionPane.YES_OPTION){
				productManager.deleteProductAtIndex(rowOfSelectedProduct);
				cardLayout.show(mainProductsPanel, PRODUCTSPANEL);
			}
		}
		txtID.setText("");txtDescr.setText("");txtQuantity.setText("");txtPrice.setText("");txtHeight.setText("");txtWeight.setText("");txtWidth.setText("");
		productsTablePanel.paintPanel();
	}
	@Override
	public void mouseClicked(MouseEvent m) {
		Point p = m.getPoint() ;
		if(m.getClickCount()==2){
			rowOfSelectedProduct = table.rowAtPoint(p);
			btnSave.show(true);
			btnDelete.show(true);//Enables deletion of a product
			btnCreate.show(false);
			lblProductForm.setText("Alter or Delete this Product");
			productFormPanel.fillFormOfPoduct(productManager.getProductAtIndex(rowOfSelectedProduct));
			cardLayout.show(mainProductsPanel, PRODUCTFORMPANEL);
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
