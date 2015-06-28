import java.io.Serializable;
import java.util.ArrayList;


public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -964045979544671492L;
	private String categoryName ;
	private int totalProductsSold ;
	private ArrayList<Product> productList ;
	private boolean beingUsed  ;
	private double totalRevenue ;//sunolika esoda apo auti tin katigoria
	
	public Category(String categoryName){
		this.categoryName = categoryName ;
		productList = new ArrayList<Product>();
		totalRevenue = 0 ;
		beingUsed = false ;		
	}
	public void addProduct(Product product){
		productList.add(product);
		beingUsed = true;
	}
	public void removeProduct(Product product){
		productList.remove(product);
	}
	public void addToTotalRevenue(double price){
		totalRevenue =+ price ;
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
