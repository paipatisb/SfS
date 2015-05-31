import java.io.Serializable;
import java.util.ArrayList;


public  class RecordManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList list ;
	private String[] cFieldNames={"Name","Last Name","Email","Phone Number","Phone Number2","Address","Address2","AFM"} ;
	private String[] pFieldNames={"ID","Description","Quantity","Price","Supplier","height","Category","Width"} ;
	private int fieldCount = 8 ;
	
	public RecordManager(){
		
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
	
	public  String[] getCustomerFieldNames(){
		return cFieldNames;
	}
	
	public  String[] getProductFieldNames(){
		return pFieldNames;

	}
	public void setCustomerFieldNames(String[] cFieldNames){
		this.cFieldNames = cFieldNames ;
	}

	public void setProductFieldNames(String[] pFieldNames) {
		this.pFieldNames = pFieldNames;
	}
	public int getFieldCount() {
		return fieldCount;
	}
	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}
}
