import java.awt.Image;
import java.io.Serializable;


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
	private int id ;
	//private Image image ;
	private double price;
	private double height ;
	private double width ;
	private String supplier ;
	private String category ;
	
	
	public Product(String id,String descr,String quantity,String price,String supplier,String height,String category,String width){
		this.id =  Integer.parseInt(id) ;
		this.price= Double.parseDouble(price) ;
		description = descr ;
		this.quantity = Integer.parseInt(quantity);
		this.supplier = supplier ;
		this.height = Double.parseDouble(height) ;
		this.category = category ;
		this.width = Double.parseDouble(width);
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getWeight() {
		return category;
	}


	public void setWeight(String acategory) {
		this.category = acategory;
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


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	

}
