import java.io.Serializable;
import java.util.ArrayList;


public class CustomerManager  implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Customer> customerList = new ArrayList() ;  ;
	public CustomerManager() {
		
		customerList.add(new Customer("Vasilis","Paipatis","M.Alexandrou 3","paipatisb@gmail.com"));
		customerList.add(new Customer("Kostas","Paipatis","M.Alexandrou 3","paipatisk@gmail.com"));
	}
	
	public void addCustomer(Customer aCustomer){
		customerList.add(aCustomer);
	}
	public void deleteCustomer(Customer aCustomer){
		customerList.remove(aCustomer);
	}
	public void deleteCustomerAtIndex(int i){
		customerList.remove(i);
	}
	public Customer getCustomerAtIndex(int i){
		return customerList.get(i);
	}

	public ArrayList<Customer> getList() {
		return customerList;
	}

	
	
	
	
}
