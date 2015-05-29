import java.util.ArrayList;


public class RecordManager {
	private ArrayList<Object> list ;
	
	public RecordManager(ArrayList<Object> aList){
		this.list = aList ;
		
		
		
	}
	public void addToList(Object o){
		list.add(o);
	}
	public void deleteProduct(Object o){
		list.remove(o);
	}
	public void deleteProductAtIndex(int i){
		list.remove(i);
	}
	public Object getObjectAtIndex(int i){
		return list.get(i);
	}
	public ArrayList<Object> getList(){
		return list ;
	}
	
}
