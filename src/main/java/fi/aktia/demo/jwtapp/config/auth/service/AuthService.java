package fi.aktia.demo.jwtapp.config.auth.service;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

public interface AuthService {
	
	public String getUsernameFromToken(String token);
	public Date getExpirationDateFromToken(String token);
	public String generateToken(String username);
	public Boolean validateToken(String token, UserDetails userDetails);
	public String refreshToken(String token);
	public Boolean canTokenBeRefreshed(String token, UserDetails userDetails);
}
