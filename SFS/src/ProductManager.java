import java.io.Serializable;
import java.util.ArrayList;




public class ProductManager extends RecordManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Product> productList ;
	
	public ProductManager(){
		productList = new ArrayList();
	}
	public void addToList(Product aProduct){
		productList.add(aProduct);
	}
	public ArrayList<Product> getList(){
		return productList ;
	}
	@Override
	public void deleteObjectAtIndex(int i) {
		productList.remove(i);
		
	}
	@Override
	public Object getObjectAtIndex(int i) {
		return productList.get(i);
	}
	
	

}
