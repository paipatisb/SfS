import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;


public class CategoryInputFrame extends JFrame implements ActionListener {
	private JButton btnOk ;
	private Product product ;
	private CategoryManager categoryManager ;
	private JList  categoryJList;
	public CategoryInputFrame(Product product,CategoryManager categoryManager) {
		this.product = product ;
		this.categoryManager = categoryManager ;
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		paintJList();
		scrollPane.setViewportView(categoryJList);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnOk = new JButton("Ok");
		panel.add(btnOk);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}
	
	public void paintJList(){
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.equals(btnOk)){
			try{
				int selectedIndex = categoryJList.getSelectedIndex() ;
				String dialogueResult = JOptionPane.showInputDialog(this, "Are you sure you want to attach this category to this product?", "Warning", JOptionPane.YES_NO_OPTION);
				if(dialogueResult.equals(JOptionPane.YES_OPTION)){
					Category selectedCategory =(Category)categoryManager.getList().get(selectedIndex) ;
					product.addCategory(selectedCategory);
					selectedCategory.addProduct(product);
					this.dispose();
				}
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(this, "Select a Category to attach to this product", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
	
}
