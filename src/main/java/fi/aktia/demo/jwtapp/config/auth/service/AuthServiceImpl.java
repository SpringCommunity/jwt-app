package fi.aktia.demo.jwtapp.config.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

@Service
public class AuthServiceImpl implements AuthService {
	
	private Logger logger = Logger.getLogger(AuthServiceImpl.class);
	final static String CLAIM_KEY_SUB = "sub";
	
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	@Override
	public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(CLAIM_KEY_SUB);
        } catch (Exception e) {
        	logger.error("::getUsernameFromToken:: "+ e);
        	username = null;
        }
        logger.info("::getUsernameFromToken:: "+ username);
        return username;
	}

	@Override
	public Date getExpirationDateFromToken(String token) {
	       Date expiration;
	        try {
	            final Claims claims = getClaimsFromToken(token);
	            expiration = claims.getExpiration();
	        } catch (Exception e) {
	        	logger.error("::getExpirationDateFromToken:: "+ e);
	            expiration = null;
	        }
	        logger.info("::getExpirationDateFromToken:: "+expiration);
	        return expiration;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		logger.info("::getClaimsFromToken:: "+claims.toString());
		return claims;
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	

	@Override
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<String, Object>();
		 claims.put(CLAIM_KEY_SUB, username);
		 return Jwts.builder()
				    .setSubject(username)
	                .setClaims(claims)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(generateExpirationDate())
	                .signWith(SignatureAlgorithm.HS512, secret)
	                .compact();
	}

	@Override
	public Boolean validateToken(String token, UserDetails userDetails) {
	      final String username = getUsernameFromToken(token);
	      return username.equalsIgnoreCase(userDetails.getUsername())
	               && !isTokenExpired(token);
	}

	@Override
	public String refreshToken(String token) {
		final String username = getUsernameFromToken(token);
		String refreshedToken;
	        try {
	            refreshedToken = generateToken(username);
	        } catch (Exception e) {
	        	logger.error("::refreshToken:: "+ e);
	            refreshedToken = null;
	        }
	        logger.info("::refreshToken:: "+refreshedToken);
	        return refreshedToken;
	}

	@Override
	public Boolean canTokenBeRefreshed(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
        return username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token);
	}

}
