import java.io.Serializable;
import java.util.ArrayList;


public class CustomerManager  implements Serializable  {
	private ArrayList<Customer> customerList = new ArrayList() ;  ;
	public CustomerManager() {
		
		customerList.add(new Customer("Vasilis","Paipatis","M.Alexandrou 3","paipatisb@gmail.com"));
		customerList.add(new Customer("Kostas","Paipatis","M.Alexandrou 3","paipatisk@gmail.com"));
	}
	
	public void addCustomer(Customer aCustomer){
		customerList.add(aCustomer);
	}
	public void deleteCustomer(Customer aCustomer){
		
	}

	public ArrayList<Customer> getList() {
		return customerList;
	}

	
	
	
	
}
