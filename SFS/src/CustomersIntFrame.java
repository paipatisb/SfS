import javax.swing.JInternalFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CustomersIntFrame extends JInternalFrame implements ActionListener {
	private JButton btnNewCustomer ;
	private JPanel tableBasePanel ,tablePanel;
	private CustomerManager customerManager ;
	private JPanel mainCustomerPanel ,newCustomerPanel = new NewCustomerPanel(customerManager);
	public CustomersIntFrame(CustomerManager customerManager) {
		super("Customers");
		this.customerManager = customerManager ;
		
		mainCustomerPanel = new JPanel();
		getContentPane().add(mainCustomerPanel, BorderLayout.CENTER);
		mainCustomerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		mainCustomerPanel.add(buttonPanel,BorderLayout.NORTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		btnNewCustomer = new JButton("New Customer");
		btnNewCustomer.addActionListener(this);
		buttonPanel.add(btnNewCustomer);
		
		tableBasePanel = new JPanel();
		mainCustomerPanel.add(tableBasePanel, BorderLayout.CENTER);
		tableBasePanel.setLayout(new BorderLayout(0, 0));
		tablePanel = new TablePanel(this.customerManager.getList());
		tableBasePanel.add(tablePanel);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnNewCustomer)){
			mainCustomerPanel.removeAll();
			mainCustomerPanel.add(newCustomerPanel);
			this.repaint();
			mainCustomerPanel.repaint();
		}
				
	}
}
