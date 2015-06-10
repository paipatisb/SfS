import java.io.Serializable;
import java.util.ArrayList;


public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -964045979544671492L;
	private String categoryName ;
	private int totalProductsSold ;
	private ArrayList<Product> productsInThisCategory ;
	private boolean beingUsed  ;
	
	public Category(String categoryName){
		this.categoryName = categoryName ;
		productsInThisCategory = new ArrayList<Product>();
		if(productsInThisCategory.size() == 0){
			beingUsed = false ;
		}
		else beingUsed = true;
		
	}
	public void addProduct(Product product){
		productsInThisCategory.add(product);
		beingUsed = true;
	}
	public void removeProduct(Product product){
		productsInThisCategory.remove(product);
	}
	
	public void setCategoryName(String newName){
		this.categoryName = newName ;
	}
	public String getCategoryName(){
		return categoryName ;
	}
	
	
	public void addToTotalProductsSold(){
		totalProductsSold++;
	}
	public void setTotalProductsSold(int count){
		totalProductsSold = count ;
	}
	public int getTotalProductsSold(){
		return totalProductsSold;
	}

	public boolean isBeingUsed() {
		return beingUsed;
	}

	public void setBeingUsed(boolean beingUsed) {
		this.beingUsed = beingUsed;
	}
}
