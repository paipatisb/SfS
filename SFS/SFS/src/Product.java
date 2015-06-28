import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;


public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5214016315870798726L;
	/**
	 * 
	 */
	
	private String description ;
	private int quantity ;
	private String ID ;
	//private Image image ;
	private double price;
	private double height ;
	private double width ;
	private String supplier ;
	private ArrayList<Category> categoryList ;
	
	public Product(String id, String descr, String quantity, String price, String supplier, String height, String width){
		this.ID =  id ;
		this.price= Double.parseDouble(price) ;
		description = descr ;
		this.quantity = Integer.parseInt(quantity);
		this.supplier = supplier ;
		this.height = Double.parseDouble(height) ;
		this.width = Double.parseDouble(width);
		categoryList = new ArrayList<Category>();
	}

	
	public String getId() {
		return ID;
	}


	public void setId(String id) {
		this.ID = id;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void setNumInStock(int numInStock) {
		this.quantity = numInStock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getSupplier() {
		return supplier;
	}


	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public ArrayList<Category> getCategoriesOfProduct(){
		return categoryList;
	}
	public void addCategory(Category category){
		categoryList.add(category);
	}

}
