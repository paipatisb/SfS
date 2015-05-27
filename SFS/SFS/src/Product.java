import java.awt.Image;


public class Product {
	private String description ;
	private int numInStock ;
	//private Image image ;
	private float price;
	
	
	public Product(String descr,int numInStock,float price){
		this.price=price ;
		description = descr ;
		this.numInStock = numInStock ;
		
	}

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumInStock() {
		return numInStock;
	}
	public void setNumInStock(int numInStock) {
		this.numInStock = numInStock;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	

}
