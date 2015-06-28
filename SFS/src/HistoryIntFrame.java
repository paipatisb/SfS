
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class HistoryIntFrame extends JInternalFrame implements MouseListener{
	private JTable table;
	private JScrollPane scrollPane ;
	private DefaultTableModel tableModel ;
	private SalesManager salesManager ;
	private JPanel  panel , masterPanel ;
	private SaleDetailsPanel saleDetailsPanel ;
	private CardLayout cardLayout ;
	public HistoryIntFrame(SalesManager salesManager) {
		this.salesManager = salesManager ;
		paintFrame();
	}
	
	public void paintFrame(){
		this.setTitle("History.");
		masterPanel = new JPanel();
		
		getContentPane().add(masterPanel, BorderLayout.CENTER);
		
		cardLayout = new CardLayout();
		masterPanel.setLayout(cardLayout);
		
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		masterPanel.add(panel);
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		saleDetailsPanel = new SaleDetailsPanel();
		
		masterPanel.add(panel,"MainPanel");
		masterPanel.add(saleDetailsPanel,"Details");
		
		cardLayout.show(masterPanel, "MainPanel");
		createTable();
	}
	public void createTable(){
		String col[] = {"Date","Customer Name","Total Cost"};
		tableModel = new DefaultTableModel(col,0); 
		table = new JTable(tableModel){
	    	 @Override
	            public boolean isCellEditable(int row , int column) {
	                return false ;
	            }
		};
		
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.getTableHeader().setReorderingAllowed(false);
	    table.addMouseListener(this);
	    ToolTipManager.sharedInstance().setDismissDelay(2000);
	    scrollPane.setViewportView(table);
	}
	public void fillTable(){
		ArrayList<Sale> tempList = salesManager.getList();
		tableModel.setNumRows(tempList.size());
		SimpleDateFormat sdf = new SimpleDateFormat();
		int j =0;
		for(Sale s : tempList){
			tableModel.setValueAt(sdf.format(s.getDate()),j,0);
			tableModel.setValueAt(s.getCustomer().getName(),j,1);
			tableModel.setValueAt(s.getTotalCost(), j,2);
		   	j++;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		Point p = m.getPoint() ;
		int rowOfSelectedSale = table.rowAtPoint(p);
		Sale selectedSale = (Sale)salesManager.getList().get(rowOfSelectedSale) ;
		 		if(m.getClickCount()==2){
		 			saleDetailsPanel.createDetailsTable();
		 			saleDetailsPanel.fillTable(selectedSale);
		 			cardLayout.show(masterPanel, "Details");
		 			
		 		}
		
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
	
	
	
	public class SaleDetailsPanel extends JPanel implements ActionListener{
		private JTable dtable ;
		private JScrollPane scrollPane ;
		private DefaultTableModel dtableModel ;
		private JButton btnGoBack ;
		public SaleDetailsPanel(){
			setLayout(new BorderLayout(0, 0));
			
			JPanel mainPanel = new JPanel();
			add(mainPanel);
			mainPanel.setLayout(new BorderLayout(0, 0));
			
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(dtable);
			
			btnGoBack = new JButton("Go Back");
			btnGoBack.addActionListener(this);
			mainPanel.add(btnGoBack,BorderLayout.SOUTH);
			mainPanel.add(scrollPane, BorderLayout.CENTER);
			createDetailsTable();
		}
		public void createDetailsTable(){
			String col[] = {"Products Purchased","Quantities of Each Product","Price per Unit"};
			dtableModel = new DefaultTableModel(col,0); 
			dtable = new JTable(dtableModel){
		    	 @Override
		            public boolean isCellEditable(int row , int column) {
		                return false ;
		            }
			};
			
			dtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			dtable.getTableHeader().setReorderingAllowed(false);
		    ToolTipManager.sharedInstance().setDismissDelay(2000);
		    scrollPane.setViewportView(dtable);
		    
		}
		public void fillTable(Sale selectedSale){
			ArrayList<Product> productsInThisSale = selectedSale.getProductList() ;
			ArrayList<Integer> quantitiesOfProducts = selectedSale.getQuantitiesList() ;
			dtableModel.setNumRows(productsInThisSale.size());
			
			for(int i=0; i<productsInThisSale.size(); i++){
				dtableModel.setValueAt(productsInThisSale.get(i).getDescription(),i,0);
				dtableModel.setValueAt(quantitiesOfProducts.get(i),i,1);
				dtableModel.setValueAt(productsInThisSale.get(i).getPrice(),i,2);
			}
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnGoBack)){
				cardLayout.show(masterPanel,"MainPanel");
			}
			
		}
		
		
		
		
		
	}
	
}






