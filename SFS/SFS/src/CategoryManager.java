import java.io.Serializable;
import java.util.ArrayList;


public class CategoryManager extends Manager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4804032411582049558L;
	private ArrayList<Category> dummyCategoryList ;
	
	public CategoryManager(){
		list = new ArrayList<Category>() ;
		dummyCategoryList = new ArrayList<Category>();
	}
	public void addtoDummyList(Category dumCat){
		dummyCategoryList.add(dumCat);
	}
	public ArrayList<Category> getDummyCategoryList(){
		return dummyCategoryList;
	}
	public void clearDummyList(){
		dummyCategoryList.clear();
	}
}
