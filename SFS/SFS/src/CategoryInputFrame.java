import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;


public class CategoryInputFrame extends JFrame implements ActionListener {
	private JButton btnOk ;
	private CategoryManager categoryManager ;
	private JComboBox comboBox ;
	private DefaultListModel<String> listModel ;
	public CategoryInputFrame(CategoryManager categoryManager,DefaultListModel<String> listModel) {
		this.categoryManager = categoryManager ;
		this.listModel = listModel ;
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		comboBox = new JComboBox();
		scrollPane.setViewportView(comboBox);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		panel.add(btnOk);
		
		paintComboBox();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
	}
	
	public void paintComboBox(){
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
		ArrayList<Category> categoryList = categoryManager.getList() ;
		for(int i=0; i<categoryList.size(); i++){
			String catName = categoryList.get(i).getCategoryName() ;
			comboBoxModel.addElement(catName);
			comboBox.setModel(comboBoxModel);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnOk)){
			try{
				int selectedIndex = comboBox.getSelectedIndex();
				int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to relate this category to this product?", "Warning", JOptionPane.YES_NO_OPTION);
				if(dialogueResult ==JOptionPane.YES_OPTION){
					
					Category selectedCategory =(Category)categoryManager.getList().get(selectedIndex) ;
					if(!categoryManager.getDummyCategoryList().contains(selectedCategory)){
						categoryManager.addtoDummyList(selectedCategory);
						listModel.addElement(selectedCategory.getCategoryName());
					}
							
					else JOptionPane.showMessageDialog(this, "Category already related to this product", "Error", JOptionPane.ERROR_MESSAGE);
					this.dispose();
				}
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(this, "Select a Category to relate to this product", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
	
}
