import java.io.Serializable;
import java.util.ArrayList;


public class CustomerManager extends RecordManager   {
	/**
	 * 
	 */
	
	private ArrayList<Customer> customerList   ;
	public CustomerManager() {
		customerList = new ArrayList();
	}


	@Override
	public void deleteObjectAtIndex(int i) {
		customerList.remove(i);
	}

	public void addToList(Customer c) {
		customerList.add(c);
	}

	@Override
	public Customer getObjectAtIndex(int i) {
		return customerList.get(i);
	}

	@Override
	public ArrayList getList() {
		return customerList;
	}

	
	
	
	
}
