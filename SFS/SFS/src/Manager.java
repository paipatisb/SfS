import java.io.Serializable;
import java.util.ArrayList;


public  class Manager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList list ;
	
	
	public Manager(){
		
	}
	
	public   void deleteObjectAtIndex(int i){
		list.remove(i);
	}
	
	public   Object getObjectAtIndex(int i){
		return list.get(i);
	}
	public  ArrayList getList(){
		return list ;
	}
}
