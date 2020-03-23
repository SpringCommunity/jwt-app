package fi.aktia.demo.jwtapp.config.auth.bean;

import java.io.Serializable;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

public class AuthResponseBean implements Serializable {

	private static final long serialVersionUID = -1470609606220492359L;

    
    private final String token;

	public AuthResponseBean(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}
    
}
