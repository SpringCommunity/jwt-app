package fi.aktia.demo.jwtapp.config.auth.bean;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

public class AuthRequestBean {

	private String username;
	private String password;
	public AuthRequestBean() {
		super();
	}
	
	public AuthRequestBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
