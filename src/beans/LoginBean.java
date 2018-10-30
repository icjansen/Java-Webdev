package beans;

public class LoginBean implements java.io.Serializable {
	private String email = null;
	private String password = null;
	private String username = null;
	
	public LoginBean() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username=username;
	}
}
