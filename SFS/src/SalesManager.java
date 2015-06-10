import java.io.Serializable;
import java.util.ArrayList;


public class SalesManager extends Manager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4071139431677816490L;
	
	
	public SalesManager(){
		list = new ArrayList<Sale>() ;
	}
	

}
