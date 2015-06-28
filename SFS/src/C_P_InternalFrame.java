import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JLayeredPane;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JList;


/*
 * Periexei ta koina components pou klironomoun to ProductIntFrame kai to CustomersIntFrame
 */
public class C_P_InternalFrame extends JInternalFrame  {
	protected static final String ALL_RECORDS = "All Records" ,FORM_RECORD = "A Record" , CATEGORY_PANEL="Category Panel" ;
	protected CardLayout cardLayout ;
	protected JButton btnCreateNewRecord  ,btnCreate , btnCancel ,  btnSave, btnDelete;
	protected JLabel lblTopOfForm;
	protected JPanel   mainPanel , allRecordsPanel , newRecordPanel ;
	protected JScrollPane scrollPane,scrollPane_1 ;
	protected JPanel boxPanel ;
	protected JPanel topButtonPalen ;
	public C_P_InternalFrame(){
		BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
		Container north = (Container)ui.getNorthPane();
		north.remove(0);
		north.validate();
		north.repaint();
		
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		cardLayout = new CardLayout(0, 0) ;
		mainPanel.setLayout(cardLayout);
		/*
		 * All reckords panel
		 */
		allRecordsPanel = new JPanel();
		allRecordsPanel.setLayout(new BorderLayout(0, 0));
		topButtonPalen = new JPanel();
		allRecordsPanel.add(topButtonPalen, BorderLayout.NORTH);
		btnCreateNewRecord = new JButton("Create New Record");
		topButtonPalen.add(btnCreateNewRecord);
		
		
		scrollPane = new JScrollPane();
		allRecordsPanel.add(scrollPane, BorderLayout.CENTER);
		
		/*
		 * new Reckord Panel (Form)
		 */
		newRecordPanel = new JPanel();
		newRecordPanel.setLayout(new BorderLayout(0, 0));
		JPanel topLblPanel = new JPanel();
		newRecordPanel.add(topLblPanel, BorderLayout.NORTH);
		lblTopOfForm = new JLabel("");
		topLblPanel.add(lblTopOfForm);
		scrollPane_1 = new JScrollPane();
		newRecordPanel.add(scrollPane_1, BorderLayout.CENTER);
		JPanel bottomButtonPanel = new JPanel();
		newRecordPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
		btnCreate = new JButton("Create");
		bottomButtonPanel.add(btnCreate);
		btnSave = new JButton("Save");
		bottomButtonPanel.add(btnSave);
		btnCancel = new JButton("Cancel");
		bottomButtonPanel.add(btnCancel);
		
		btnDelete = new JButton("Delete");
		bottomButtonPanel.add(btnDelete);
		
		boxPanel = new JPanel();
		
		boxPanel.setLayout(new BorderLayout(0, 0));
		scrollPane_1.setViewportView(boxPanel);
		
		
		
		mainPanel.add(allRecordsPanel, ALL_RECORDS);
		mainPanel.add(newRecordPanel, FORM_RECORD);
	}

	
	
	

	

}
