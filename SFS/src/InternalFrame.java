import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;


public class InternalFrame extends JInternalFrame {
	
	public InternalFrame(){
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		mainPanel.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		JPanel allProductsPanel = new JPanel();
		layeredPane.add(allProductsPanel, BorderLayout.CENTER);
		layeredPane.setLayer(allProductsPanel, 0);
		allProductsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel topButtonPanel = new JPanel();
		allProductsPanel.add(topButtonPanel, BorderLayout.NORTH);
		
		JButton btnNewRecord = new JButton("New Record");
		topButtonPanel.add(btnNewRecord);
		
		JPanel tableBasePanel = new JPanel();
		allProductsPanel.add(tableBasePanel, BorderLayout.CENTER);
		tableBasePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel newRecordPanel = new JPanel();
		layeredPane.setLayer(newRecordPanel, 1);
		layeredPane.add(newRecordPanel, BorderLayout.CENTER);
		newRecordPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel lblPanel = new JPanel();
		newRecordPanel.add(lblPanel, BorderLayout.NORTH);
		
		JLabel lblNewRecord = new JLabel("New Record");
		lblPanel.add(lblNewRecord);
		
		JPanel bottomButtonPanel = new JPanel();
		newRecordPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
		
		JButton btnCreate = new JButton("Create");
		bottomButtonPanel.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		bottomButtonPanel.add(btnCancel);
		
		JPanel formPanel = new JPanel();
		newRecordPanel.add(formPanel, BorderLayout.CENTER);
		formPanel.setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		formPanel.add(verticalBox, BorderLayout.WEST);
		
	}
}
