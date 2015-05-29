import java.awt.Image;


public class Product {
	private String description ;
	private int quantity ;
	private int id ;
	//private Image image ;
	private double price;
	private double height ;
	private double width ;
	private double weight ;
	
	
	public Product(int id,String descr,int quantity,double price){
		this.id = id ;
		this.price=price ;
		description = descr ;
		this.quantity = quantity ;
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


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
