import java.io.Serializable;
import java.util.ArrayList;


public class CategoryManager extends Manager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4804032411582049558L;

	public CategoryManager(){
		list = new ArrayList<Category>() ;
	}
	
	
}
