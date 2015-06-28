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
	private ArrayList<Product> productsSoldList ;
	private ArrayList<Integer> quantitiesSoldList; 
	private double totalCost ;
	
	public Sale() {
		productsSoldList = new ArrayList<Product>() ;
		quantitiesSoldList = new ArrayList<Integer>();
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
	public void setDate(Date  newDate){
		date = newDate ;
	}

	public ArrayList<Product> getProductList() {
		return productsSoldList;
	}
	public void addProduct(Product aProduct,int quant){
		productsSoldList.add(aProduct);
		quantitiesSoldList.add(quant);
	}
	public void removeProduct(int i){
		productsSoldList.remove(i);
	}

	public double getTotalCost() {
		return totalCost;
	}
	public void calculateTotalCost(){
		totalCost=0;
		for(int i=0; i<productsSoldList.size(); i++){
			Product p = productsSoldList.get(i);
			int q = quantitiesSoldList.get(i);
			
			totalCost += (p.getPrice()*q) ;
		}
	}
	public void setTotalCost(double totalCost){
		this.totalCost = totalCost ;
	}
	public ArrayList<Integer> getQuantitiesList(){
		return quantitiesSoldList;
	}
	
}
