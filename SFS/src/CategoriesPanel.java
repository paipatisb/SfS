import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;


public class CategoriesPanel extends JPanel implements ActionListener{
	private JButton btnNewCategory , btnDeleteCategory , btnCancel ;
	private JScrollPane scrollPane ;
	private String newCatName = null ;
	private JList<String> categoryJList ;
	private CategoryManager categoryManager;
	private CardLayout parentCardLayout  ;
	private JPanel parentPanel , midPanel ;
	public CategoriesPanel(CategoryManager categoryManager,JPanel parentPanel,CardLayout parentCardLayout) {
		this.categoryManager = categoryManager ;
		this.parentCardLayout = parentCardLayout ;
		this.parentPanel = parentPanel ;
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		
		btnNewCategory = new JButton("New Category");
		btnNewCategory.addActionListener(this);
		topPanel.add(btnNewCategory);
		
		btnDeleteCategory = new JButton("Delete Category");
		btnDeleteCategory.addActionListener(this);
		topPanel.add(btnDeleteCategory);
		
		midPanel = new JPanel();
		add(midPanel, BorderLayout.CENTER);
		midPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCategories = new JLabel("Categories :");
		midPanel.add(lblCategories, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		midPanel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		midPanel.add(panel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		panel.add(btnCancel);
		btnCancel.addActionListener(this);
		paintPanel();
	}
	
	public void paintPanel(){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<Category> categoryList = categoryManager.getList() ;
		System.out.printf("%d", categoryList.size());
		for(int i=0; i<categoryList.size(); i++){
			String catName = categoryList.get(i).getCategoryName() ;
			listModel.addElement(catName);
		}
		categoryJList = new JList() ;
		categoryJList.setModel(listModel);
		categoryJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(categoryJList);
		midPanel.repaint();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnNewCategory)){
			newCatName = JOptionPane.showInputDialog(this, "Enter the new Category Name");
			categoryManager.getList().add(new Category(newCatName));
			FileManager.saveToFile(MainFrame.PRODUCT_CATEGORIES_FILE_NAME, categoryManager);
			paintPanel();
		}
		else if(e.getSource().equals(btnDeleteCategory)){
				
			int  indexOfSelectedCat = categoryJList.getSelectedIndex();
			try{
				Category selectedCategory = (Category) categoryManager.getList().get(indexOfSelectedCat);
				int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Category?", "Warning!", JOptionPane.YES_NO_OPTION);
				if(selectedCategory.isBeingUsed()){
					JOptionPane.showMessageDialog(this, "This category has products attached to it.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					categoryManager.getList().remove(indexOfSelectedCat); 
					FileManager.saveToFile(MainFrame.PRODUCT_CATEGORIES_FILE_NAME, categoryManager);
					paintPanel();
				}
				
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(this, "Select the Category you want to delete.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		else if(e.getSource().equals(btnCancel)){
			int dialogueResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to leave this page?", "Warning!", JOptionPane.YES_NO_OPTION);	
			if(dialogueResult==JOptionPane.YES_OPTION){
				parentCardLayout.first(parentPanel);
			}
		}
		
	}

}
