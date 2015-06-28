import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7020619477594468968L;
	private String username;
	private String password;
	private String email;
	private int loginAtempts;
	private boolean adminRights;
	private Date dateOfregistration;
	public Date getDateOfregistration() {
		return dateOfregistration;
	}

	public void setDateOfregistration(Date dateOfregistration) {
		this.dateOfregistration = dateOfregistration;
	}

	public Date getDateOfLastLogin() {
		return dateOfLastLogin;
	}

	public void setDateOfLastLogin(Date dateOfLastLogin) {
		this.dateOfLastLogin = dateOfLastLogin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private Date dateOfLastLogin;
	
	
	public User(String username, String password,String email,
			boolean adminRights) {
		super();
		this.username = username;
		this.password = password;
		this.loginAtempts = 0;
		this.email = email;
		this.adminRights = adminRights;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getLoginAtempts() {
		return loginAtempts;
	}

	public void setLoginAtempts(int loginAtempts) {
		this.loginAtempts = loginAtempts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdminRights() {
		return adminRights;
	}

	public void setAdminRights(boolean adminRights) {
		this.adminRights = adminRights;
	}
	
	
	
}
