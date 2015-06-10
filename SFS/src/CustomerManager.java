import java.io.Serializable;
import java.util.ArrayList;


public class CustomerManager extends Manager   {
	
	private static final long serialVersionUID = 4622993822073711968L;
	private String[] cFieldNames={"Name","Email","Phone Number","Phone Number2","Address","Address2","AFM"} ;
	private int fieldCount = 7 ;

	/**
	 * 
	 */
	
	public CustomerManager() {
		list = new ArrayList<Customer>();
	}
	
	public  String[] getCustomerFieldNames(){
		return cFieldNames;
	}
	public int getFieldCount() {
		return fieldCount;
	}
	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}
	public Customer getCustomer(String aName){
		for(Customer c :(ArrayList<Customer>)list){
			if(c.getName().equalsIgnoreCase(aName)){
				return c ;
			}
		}
		return null ;
	}
	
}
