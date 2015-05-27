import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TablePanel extends JPanel {
	private JTable table;
	private ArrayList<Customer> customerList ;
	private DefaultTableModel model ;
	private JScrollPane scrollPane = new JScrollPane(table);
	
	public TablePanel(ArrayList<Customer> customerList) {
		this.customerList = customerList ;
		setLayout(new BorderLayout(0, 0));
		this.fillTable();
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	private void fillTable(){
		String col[] = {"ID","Name","Last Name","Address","e-mail"};
		model = new DefaultTableModel(col,customerList.size()); 
		table = new JTable(model){
	    	 @Override
	            public boolean isCellEditable(int row , int column) {
	                return false ;
	            }
	    };
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
