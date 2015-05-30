import java.io.Serializable;
import java.util.ArrayList;


public class Customer  {
	private String name;
	private String lastName ;
	private String address ,address2;
	private String phoneNumber ,phoneNumber2 ;
	private String email ;
	private String AFM ;
	private ArrayList<Sale> purchases ; 
	
	
	public Customer(String name, String lastName,  String email,String phoneNumber,String phoneNumber2,String address, String address2 ,String AFM) {
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNumber =phoneNumber ;
		this.phoneNumber2 = phoneNumber2 ;
		this.address2 = address2 ;
		this.AFM = AFM ;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getPhoneNumber2() {
		return phoneNumber2;
	}


	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}


	public String getAFM() {
		return AFM;
	}


	public void setAFM(String aFM) {
		AFM = aFM;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	
}
