package fi.aktia.demo.jwtapp.service;

import java.util.Date;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {
	
	public String getUsernameFromToken(String jwt);
	public Date getCreatedDateFromToken(String jwt);
	public Date getExpirationDateFromToken(String jwt);
	String generateToken(Map<String, Object> claims);
	public Boolean validateToken(String jwt, UserDetails userDetails);
}
