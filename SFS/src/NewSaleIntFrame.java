import javax.swing.JInternalFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;


public class NewSaleIntFrame extends JInternalFrame implements ActionListener,MouseListener  {
	private static final String MAINPANEL = "Main Panel" ;
	private JTextField txtSearchForCustomer;
	private JButton bottomButton_Panel ;
	private ProductManager productManager;
	private CustomerManager customerManager ;
	private ArrayList<Product> productList ;
	private ArrayList<Customer> customerList ;
	private ArrayList<JButton> addButtonList , removeButtonList ;
	private JPanel newSalePanel;
	private JPanel bottomButtonPanel;
	private JButton btnAddToCart;
	private JButton btnGoToCart;
	private JScrollPane scrollPane;
	private JTable table;
	private CardLayout cardLayout;
	
	public NewSaleIntFrame(ProductManager productManager,CustomerManager customerManager) {
		this.productManager = productManager ;
		productList = productManager.getList() ;
		this.customerManager = customerManager ;
		customerList = customerManager.getList() ;
		
		BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
		Container north = (Container)ui.getNorthPane();
		north.remove(0);
		north.validate();
		north.repaint();
		
		cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);
		
		newSalePanel = new JPanel();
		newSalePanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(newSalePanel,MAINPANEL);
		
		
		
		JPanel topSearch_Panel = new JPanel();
		newSalePanel.add(topSearch_Panel, BorderLayout.NORTH);
		topSearch_Panel.setLayout(new BoxLayout(topSearch_Panel, BoxLayout.X_AXIS));
		
		JLabel lblSearchForA = new JLabel("Customer :");
		topSearch_Panel.add(lblSearchForA);
		
		txtSearchForCustomer = new JTextField();
		topSearch_Panel.add(txtSearchForCustomer);
		txtSearchForCustomer.setColumns(10);
		
		bottomButtonPanel = new JPanel();
		newSalePanel.add(bottomButtonPanel, BorderLayout.SOUTH);
		
		btnAddToCart = new JButton("Add to Cart");
		bottomButtonPanel.add(btnAddToCart);
		
		btnGoToCart = new JButton("Go to Cart");
		bottomButtonPanel.add(btnGoToCart);
		
		scrollPane = new JScrollPane();
		newSalePanel.add(scrollPane, BorderLayout.CENTER);
		
		createTable() ;
	}
	
	
	private void createTable(){
		String col[] = {"ID","Description","Quantity","Price"};
		DefaultTableModel model = new DefaultTableModel(col,productList.size());
	    ToolTipManager.sharedInstance().setDismissDelay(2000);
	    
	    table = new JTable(model){
	    	 @Override
	            public boolean isCellEditable(int row , int column) {
	                return false ;
	            }
	    };
	    int j =0;
		for(Product p :productList){
			
	    	table.setValueAt(p.getId(),j,0);
	    	table.setValueAt(p.getDescription(), j, 1);
	    	table.setValueAt(p.getQuantity(),j,2);
	    	table.setValueAt(p.getPrice(),j,3);
	    	j++;
		}
		scrollPane.setViewportView(table);
		getContentPane().repaint();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
