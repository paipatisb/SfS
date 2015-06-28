import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class LoginFrame extends JFrame implements ActionListener{
	protected static final String LOGIN_FRAME = "login frame",REGISTER_FRAME="register_frame";
	public static final String USER_FILE_NAME = "users.ser";
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginButton,registerButton;
	private UserManager userManager = null;
	private CardLayout cardLayout;
	private JPanel masterPanel;
	private RegisterPanel registerPanel;
	
	public LoginFrame() {
		super("Solution For Sales.");
		userManager = (UserManager)FileManager.loadFromFile(MainFrame.USER_FILE_NAME);
		
		JPanel loginPanel = new JPanel();	
		cardLayout = new CardLayout(0,0);
		masterPanel = new JPanel(cardLayout);
		registerPanel = new RegisterPanel(userManager,masterPanel,cardLayout);
		getContentPane().add(masterPanel);
		loginPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLoginAuthentication = new JLabel("Login Authentication");
		lblLoginAuthentication.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoginAuthentication.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel.add(lblLoginAuthentication, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		loginPanel.add(panel_1, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		passwordField = new JPasswordField();
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(106, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(122, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(73)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		loginPanel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		loginButton = new JButton("login");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		loginButton.addActionListener(this);
		panel_2.add(loginButton);
		
		registerButton = new JButton("register");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerButton.addActionListener(this);
		panel_2.add(registerButton);
		masterPanel.add(loginPanel, LOGIN_FRAME);
		masterPanel.add(registerPanel,REGISTER_FRAME);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		
	}
	private void clearFields(int errCode){
		if (errCode == 1)
		passwordField.setText("");
		else{
		passwordField.setText("");
		textField.setText("");
	}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.loginButton)){
			User user = userManager.returnUser(textField.getText());
			if (user.equals(null))
				JOptionPane.showMessageDialog(this, "Wrong Username,Make sure you typed your username correctly or register","Error",JOptionPane.ERROR_MESSAGE);
			else
			if(user.getPassword().equals(passwordField.getText())){
			JOptionPane.showMessageDialog(this, "Succesfull login","Succes",JOptionPane.INFORMATION_MESSAGE);
			user.setDateOfLastLogin(new Date());
			FileManager.saveToFile(USER_FILE_NAME, userManager);
			new MainFrame(user,userManager);
			}
			else{
			clearFields(1);
			JOptionPane.showMessageDialog(this, "Failled Atempt, please enter your password correctly","Error",JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		}
		else if(e.getSource().equals(this.registerButton)){
			cardLayout.show(masterPanel, REGISTER_FRAME);
		}
	}
}
