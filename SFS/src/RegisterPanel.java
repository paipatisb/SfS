import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.FlowLayout;
import java.util.Date;

import net.miginfocom.swing.MigLayout;


public class RegisterPanel extends JPanel implements ActionListener {
	
	private static final String ADMIN_PASS = "ok"; 
	private JTextField emailTxtBox;
	private JPasswordField passwordFieldConfirmationTxtBox;
	private JPasswordField passwordTxtBox;
	private JTextField usernameTxtBox;
	private JPasswordField administratorPasswordTxtBox;
	private JButton registerButton;
	private JButton clearFieldsButton;
	private UserManager uManager;
	private JPanel prev;
	private CardLayout cardLayout;
	public RegisterPanel(UserManager userManager,JPanel card,CardLayout cardLay) {
		this.uManager = userManager;
		this.prev = card;
		this.cardLayout = cardLay;
		paintMainFrame();
	}
	private void paintMainFrame(){
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblAdministratorCode = new JLabel("Administrator Code:");
		lblAdministratorCode.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_2 = new JLabel("Password Confirmation:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(lblAdministratorCode, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(29)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAdministratorCode, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		
		panel.add(panel_2);
		
		emailTxtBox = new JTextField();
		emailTxtBox.setColumns(10);
		
		passwordFieldConfirmationTxtBox = new JPasswordField();
		
		passwordTxtBox = new JPasswordField();
		passwordTxtBox.setToolTipText("please make sure your password is 6 characters in length or more.");
		
		usernameTxtBox = new JTextField();
		usernameTxtBox.setColumns(10);
		
		administratorPasswordTxtBox = new JPasswordField();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(administratorPasswordTxtBox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.addComponent(usernameTxtBox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addComponent(passwordTxtBox, Alignment.LEADING)
						.addComponent(passwordFieldConfirmationTxtBox, Alignment.LEADING)
						.addComponent(emailTxtBox, Alignment.LEADING))
					.addGap(48))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(30)
					.addComponent(usernameTxtBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(passwordTxtBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(passwordFieldConfirmationTxtBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(emailTxtBox, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(administratorPasswordTxtBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(this);
		panel_3.add(registerButton);
		
		clearFieldsButton = new JButton("Clear Fields");
		clearFieldsButton.addActionListener(this);
		panel_3.add(clearFieldsButton);
		
		JLabel lblRegisterNewUser = new JLabel("Register new user");
		add(lblRegisterNewUser, BorderLayout.NORTH);
		lblRegisterNewUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegisterNewUser.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVisible(true);
	}
	private void clearFields(){
		usernameTxtBox.setText("");
		passwordTxtBox.setText("");
		passwordFieldConfirmationTxtBox.setText("");
		emailTxtBox.setText("");
		administratorPasswordTxtBox.setText("");
	}
		
	public void actionPerformed(ActionEvent e){
	if(e.getSource().equals(registerButton)){
		User user = null;
		if(passwordTxtBox.getText().length()<6)
		 JOptionPane.showMessageDialog(this,"Your Password is too short, make sure it is more than 6 characters","Error",JOptionPane.ERROR_MESSAGE );
		else{	
				if (!passwordTxtBox.getText().equals(passwordFieldConfirmationTxtBox .getText()))
					JOptionPane.showMessageDialog(this,"Your Passwords does not match","Error",JOptionPane.ERROR_MESSAGE );
					else{
						if(administratorPasswordTxtBox.getText().equals(ADMIN_PASS)){
							user = new User(usernameTxtBox.getText(),passwordTxtBox.getText(),emailTxtBox.getText(),true);
							user.setDateOfregistration(new Date());
							uManager.addUser(user);
							JOptionPane.showMessageDialog(this,"Registration Completed","succes!",JOptionPane.INFORMATION_MESSAGE);
							FileManager.saveToFile(LoginFrame.USER_FILE_NAME, uManager);
							cardLayout.show(prev,LoginFrame.LOGIN_FRAME);
						}
						else {
							user = new User(usernameTxtBox.getText(),passwordTxtBox.getText(),emailTxtBox.getText(),false);
							user.setDateOfregistration(new Date());
							uManager.addUser(user);
							JOptionPane.showMessageDialog(this,"Registration Completed","succes!",JOptionPane.INFORMATION_MESSAGE);
							FileManager.saveToFile(LoginFrame.USER_FILE_NAME, uManager);
							cardLayout.show(prev,LoginFrame.LOGIN_FRAME);
						}
					}
				
		}
		clearFields();
			}
	else if(e.getSource().equals(clearFieldsButton)){
		clearFields();
	}
	}
}
