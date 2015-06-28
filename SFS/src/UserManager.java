import java.io.Serializable;
import java.util.ArrayList;




public class UserManager extends Manager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4318140937232677301L;
	
	public UserManager(){
		list = new ArrayList();
	}
	public void addUser(User u){
		list.add(u);
	}
	public User returnUser(String name){
		for (User u: (ArrayList<User>)list){
			if (u.getUsername().equals(name))
			return u;
		}
		return null;
	}
	public ArrayList<User> getList(){
		return this.list;
	}
}
