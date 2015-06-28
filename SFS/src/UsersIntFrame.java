import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;


public class UsersIntFrame extends JInternalFrame implements ActionListener,MouseListener{
		
		
	
	private UserManager userManager;
	private JTable table;
	private JScrollPane scrollPane ;
	public UsersIntFrame(UserManager userManager) {
		this.userManager = userManager ;
		
		
		scrollPane = new JScrollPane() ;
		add(scrollPane);
		
		this.setTitle("Users");
		CreateTable();
	}
	private void CreateTable(){
		String col[] = {"UserName","e-mail","Administrator","Last Login","Date of registration"};
		DefaultTableModel tableModel = new DefaultTableModel(col,userManager.getList().size());
		table = new JTable(tableModel){
	    	 @Override
	            public boolean isCellEditable(int row , int column) {
	                return false ;
	            }
	    };
		table.addMouseListener(this);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setToolTipText("Double click on a user to edit his information");
	    table.getTableHeader().setReorderingAllowed(false);
	    ToolTipManager.sharedInstance().setDismissDelay(2000);
	    scrollPane.setViewportView(table);
	}
	public void fillTable(){
		int i=0;
		for (User u: userManager.getList()){
			table.setValueAt(u.getUsername(),i,0);
			table.setValueAt(u.getEmail(),i,1);
			table.setValueAt(u.isAdminRights(),i,2);
			table.setValueAt(u.getDateOfLastLogin(),i,3);
			table.setValueAt(u.getDateOfregistration(),i,4);
			i++;
		}
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
