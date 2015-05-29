import java.io.Serializable;
import java.util.ArrayList;




public class ProductManager implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Product> productList = new ArrayList();
	
	public ProductManager(){
		productList.add(new Product(1111,"�������� ��������� ������",4,50.00));
		productList.add(new Product(1112,"�������� ������� ������",4,25.00));
	}
	public void addProduct(Product aProduct){
		productList.add(aProduct);
	}
	public ArrayList<Product> getList(){
		return productList ;
	}
	
	

}
