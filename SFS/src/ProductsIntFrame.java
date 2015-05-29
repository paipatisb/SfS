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
	private JButton btnSave ,btnCancel ;
	private JTable table;
	private MouseListener mListener = this ;
	public ProductsIntFrame(JFrame mainFrame,ProductManager productManager) {
		super("Products");
		this.productManager = productManager;
		paintMainProductsPanel() ;
		
		btnNewProduct.addActionListener(this);
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		
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
			
			Box txtBox = Box.createVerticalBox() ;
			
			txtID = new JTextField();
			txtID.setPreferredSize(new Dimension(6, 17));
			txtBox.add(txtID);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			
			txtDescr = new JTextField();
			txtDescr.setPreferredSize(new Dimension(6, 17));
			txtBox.add(txtDescr);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			
			txtQuantity = new JTextField();
			txtQuantity.setPreferredSize(new Dimension(6, 17));
			txtBox.add(txtQuantity);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			
			txtPrice = new JTextField();
			txtPrice.setPreferredSize(new Dimension(6, 17));
			txtBox.add(txtPrice);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			
			txtHeight = new JTextField();
			txtHeight.setPreferredSize(new Dimension(6, 17));
			txtBox.add(txtHeight);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			
			txtWidth = new JTextField();
			txtWidth.setPreferredSize(new Dimension(6, 17));
			txtBox.add(txtWidth);
			txtBox.add(Box.createVerticalStrut(25));
			txtBox.add(Box.createVerticalGlue());
			
			txtWeight = new JTextField();
			txtWeight.setPreferredSize(new Dimension(6, 17));
			txtBox.add(txtWeight);
			Component verticalStrut = Box.createVerticalStrut(25);
			txtBox.add(Box.createVerticalGlue());
			txtBox.add(verticalStrut);
			
			
			Box lblBox = Box.createVerticalBox();
			
			JLabel lblID = new JLabel("ID :");
			lblID.setMaximumSize(new Dimension(2147483647, 2147483647));
			lblID.setPreferredSize(new Dimension(80, 17));
			lblBox.add(lblID);
			lblBox.add(Box.createVerticalStrut(25));
			
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
			
			
			btnSave = new JButton("Save");
			btnCancel = new JButton("Cancel");
			
			Box buttonBox = Box.createHorizontalBox();
			buttonBox.add(btnSave);
			buttonBox.add(btnCancel);
			
			JLabel lblProductDetails = new JLabel("Product Details.");
			add(lblProductDetails,BorderLayout.NORTH);
			add(lblBox,BorderLayout.WEST);
			add(txtBox,BorderLayout.CENTER);
			add(buttonBox,BorderLayout.SOUTH);		
		}
		/*
		 * Fills in the information form of a specific product 
		 */
		public void fillFormOfProduct(Product p){
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
		}
		else if(e.getSource().equals(btnSave)){
			int dialogResult = JOptionPane.showConfirmDialog (this, "Save Product?","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult==JOptionPane.YES_OPTION){
				productManager.addProduct(new Product(
						Integer.parseInt(txtID.getText()),
						txtDescr.getText().toUpperCase(),
						Integer.parseInt(txtQuantity.getText()),
						Double.parseDouble(txtPrice.getText())));
				txtID.setText("");txtDescr.setText("");txtQuantity.setText("");txtPrice.setText("");
				productsTablePanel.paintPanel();
				tableBasePanelCardLayout.show(tableBasePanel, TABLEBASEPANEL);
				cardLayout.show(mainProductsPanel, PRODUCTSPANEL);
			}
		}
		else if(e.getSource().equals(btnCancel)){
			txtID.setText("");txtDescr.setText("");txtQuantity.setText("");txtPrice.setText("");txtHeight.setText("");txtWeight.setText("");txtWidth.setText("");
			cardLayout.show(mainProductsPanel, PRODUCTSPANEL);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent m) {
		Point p = m.getPoint() ;
		int row = table.rowAtPoint(p);
		if(m.getClickCount()==2){
			productFormPanel.fillFormOfProduct(productManager.getList().get(row));
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
