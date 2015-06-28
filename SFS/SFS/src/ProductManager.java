import java.io.Serializable;
import java.util.ArrayList;




public class ProductManager extends Manager implements Serializable {
	private String[] pFieldNames={"ID","Description","Quantity","Price","Supplier","height","Width","Product Categories"} ;
	private int fieldCount = 7 ;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8197224361438375253L;
	/**
	 * 
	 */
	public ProductManager(){
		list = new ArrayList<Product>();
	}
	
	public ArrayList<Product> searchForProduct(String s_Value){
		ArrayList<Product> dummyList = new ArrayList<Product>() ;
		if(s_Value.matches("[0-9]+")){
			for(Product p : (ArrayList<Product>)list){
				if(p.getId().equals(s_Value)){
					dummyList.add(p);
				}
			}
		}
		else{
			for(Product p : (ArrayList<Product>)list){
				if(p.getDescription().contains(s_Value)){
					dummyList.add(p);
				}
			}
		}
		return dummyList;
	}
	public Product getProductWithID(String id){
		for(Product p : (ArrayList<Product>)list){
			if(p.getId().contains(id)){
				return p;
			}
		}
		return null ;
	}
	
	public  String[] getProductFieldNames(){
		return pFieldNames;

	}
	public int getFieldCount() {
		return fieldCount;
	}
	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}
}
