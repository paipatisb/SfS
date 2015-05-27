import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class NewCustomerPanel extends JPanel implements ActionListener{
	private JTextField txtName;
	private JTextField txtLastName;
	private JTextField txtAddress;
	private JTextField txtEmail;
	private JButton btnOk,btnCancel ;
	private CustomerManager customerManager ;
	public NewCustomerPanel(CustomerManager customerManager) {
		setLayout(null);
		this.customerManager = customerManager ;
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(30, 45, 75, 14);
		add(lblName);
		
		JLabel lblLastName = new JLabel("Last Name :");
		lblLastName.setBounds(30, 70, 75, 14);
		add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(30, 95, 75, 14);
		add(lblAddress);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setBounds(30, 120, 75, 14);
		add(lblEmail);
		
		txtName = new JTextField();
		txtName.setBounds(140, 42, 125, 20);
		add(txtName);
		txtName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(140, 67, 125, 20);
		add(txtLastName);
		txtLastName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(140, 92, 125, 20);
		add(txtAddress);
		txtAddress.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(140, 117, 125, 20);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		btnOk = new JButton("Ok");
		btnOk.setBounds(30, 180, 89, 23);
		add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(140, 180, 89, 23);
		add(btnCancel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnOk)){
			customerManager.addCustomer(
					new Customer(txtName.getText(),txtLastName.getText(),txtAddress.getText(),txtEmail.getText()));
		}
		
	}
}
