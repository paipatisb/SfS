import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;



public class MainFrame extends JFrame implements ActionListener {
	private JButton customersButton,newSaleButton,productsButton,historyButton ;
	private CustomerManager customerManager =new CustomerManager() ;
	private JInternalFrame customersIntFrame = new CustomersIntFrame(this,customerManager) ;
	private JPanel right_panel,left_panel ;
	public MainFrame() {
		super("Solution For Sales.");
		Dimension btnSize = new Dimension();
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane);
		splitPane.setEnabled(false);
		
		left_panel = new JPanel();
		splitPane.setLeftComponent(left_panel);
		left_panel.setLayout(new BoxLayout(left_panel, BoxLayout.Y_AXIS));
		
		customersButton = new JButton("Customers");
		customersButton.addActionListener(this);
		btnSize = customersButton.getSize() ;
		left_panel.add(customersButton);
		
		productsButton = new JButton("Products");
		productsButton.setSize(btnSize);
		left_panel.add(productsButton);
		
		newSaleButton = new JButton("New Sale");
		newSaleButton.setSize(btnSize);
		left_panel.add(newSaleButton);
		
		historyButton = new JButton("History");
		historyButton.setSize(btnSize);
		left_panel.add(historyButton);
		
		right_panel = new JPanel();
		splitPane.setRightComponent(right_panel);
		right_panel.setLayout(new BorderLayout(0, 0));
		
		right_panel.add(customersIntFrame, BorderLayout.CENTER);
		customersIntFrame.setVisible(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(customersButton)){
			right_panel.removeAll();
			right_panel.add(customersIntFrame, BorderLayout.CENTER);
			customersIntFrame.setVisible(true);
			this.repaint();
		}
		
	}
}