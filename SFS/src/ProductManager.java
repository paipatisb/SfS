import java.io.Serializable;
import java.util.ArrayList;




public class ProductManager extends Manager implements Serializable {
	private String[] pFieldNames={"ID","Description","Quantity","Price","Supplier","height","Width","Product Categories"} ;
	private int fieldCount = 8 ;
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
