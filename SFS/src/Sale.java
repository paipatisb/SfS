import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Sale implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3419437755682306776L;
	private Customer customer ;
	private Date date ;
	private ArrayList<Product> productList ;
	private ArrayList<Integer> quantities ;
	private double totalCost ;
	
	public Sale() {
		productList = new ArrayList<Product>() ;
		quantities = new ArrayList<Integer>() ;
	}
	public void setCustomer(Customer aCustomer){
		customer = aCustomer ;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Date getDate() {
		return date;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}
	public void addProduct(Product aProduct){
		productList.add(aProduct);
	}
	public void removeProduct(int i){
		productList.remove(i);
	}

	public double getTotalCost() {
		return totalCost;
	}
	public void calculateTotalCost(){
		for(Product p : productList){
			totalCost =+ p.getPrice() ;
		}
	}
	
}
