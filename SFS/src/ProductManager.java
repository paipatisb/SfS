import java.io.Serializable;
import java.util.ArrayList;




public class ProductManager implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Product> productList = new ArrayList();
	
	public ProductManager(){
		productList.add(new Product(1111,"пкастийо тяапефайи еновгс",4,50.00));
		productList.add(new Product(1112,"пкастийг йаяейка еновгс",4,25.00));
	}
	public void addProduct(Product aProduct){
		productList.add(aProduct);
	}
	public void deleteProduct(Product aProduct){
		productList.remove(aProduct);
	}
	public void deleteProductAtIndex(int i){
		productList.remove(i);
	}
	public Product getProductAtIndex(int i){
		return productList.get(i);
	}
	public ArrayList<Product> getList(){
		return productList ;
	}
	
	

}
