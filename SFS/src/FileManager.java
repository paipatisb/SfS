import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public  class FileManager {
	
	
	public FileManager(){
		
	}
	
	public static  RecordManager loadFromFile(String fileName){
		RecordManager recordManager = null ;
		try{
			FileInputStream fileIn = 
					new FileInputStream(MainFrame.SAVE_FOLDER+File.separator+fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			if(fileName.equals(MainFrame.CUSTOMERS_FILE_NAME)){
				recordManager = (CustomerManager)in.readObject();
			}
			else if (fileName.equals(MainFrame.PRODUCTS_FILE_NAME)){
				recordManager =(ProductManager)in.readObject();
			}
			fileIn.close();
			in.close();
		}
		catch(Exception e){
			System.out.println("Cannot read file "+fileName+"\n");
		}
		if(recordManager == null){
			if(fileName.equals(MainFrame.CUSTOMERS_FILE_NAME)){
				recordManager = new CustomerManager();
			}
			else if (fileName.equals(MainFrame.PRODUCTS_FILE_NAME)){
				recordManager = new ProductManager();
			}
			System.out.printf("Have Just  created Manager %s\n",fileName);
		}
		return recordManager ;
	}
	
	
	
	
	
	public static void saveToFile(String fileName,RecordManager recordManager){
		try{
			File outDir = new File(MainFrame.SAVE_FOLDER);
			if(!outDir.exists()){
				outDir.mkdir();
				System.out.println("Have just created an outDir");
			}
			FileOutputStream fos 
			= new FileOutputStream(outDir+
									File.separator+fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			if(recordManager != null){
				oos.writeObject(recordManager);
				System.out.printf("Just saved %s",fileName);
			}	
			oos.close();
			fos.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		
	}
		

	
}
