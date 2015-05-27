import java.util.ArrayList;
import java.util.Date;


public class Sale {
	private String customerName ;
	private Date date ;
	private ArrayList<Product> productList ;
	private float totalCost ;
	
	public Sale(String customerName, Date date,ArrayList<Product> productList) {
		this.customerName = customerName;
		this.date = date;
		this.productList = productList;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Date getDate() {
		return date;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public float getTotalCost() {
		return totalCost;
	}
	
	
}
