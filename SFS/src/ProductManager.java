import java.io.Serializable;
import java.util.ArrayList;




public class ProductManager extends RecordManager implements Serializable {
	
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
}
