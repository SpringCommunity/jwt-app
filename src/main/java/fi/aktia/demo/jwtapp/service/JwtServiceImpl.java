package fi.aktia.demo.jwtapp.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import fi.aktia.demo.jwtapp.bean.UserBean;
import fi.aktia.demo.jwtapp.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtServiceImpl implements JwtService {
	
    static final String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public String getUsernameFromToken(String jwt) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(jwt);
            int userId = Integer.parseInt(claims.getSubject());
            UserBean user = userRepo.findById(userId).get();
            username = (user != null)? user.getUsername() : null;
        } catch (Exception e) {
        	username = null;
        }
        return username;
	}

	@Override
	public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
	}

	@Override
	public Date getExpirationDateFromToken(String token) {
	       Date expiration;
	        try {
	            final Claims claims = getClaimsFromToken(token);
	            expiration = claims.getExpiration();
	        } catch (Exception e) {
	            expiration = null;
	        }
	        return expiration;
	}

	private Claims getClaimsFromToken(String jwt) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
		} catch (Exception e) {
			claims = null;
		}
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
	public String generateToken(Map<String, Object> claims) {
		 return Jwts.builder()
	                .setClaims(claims)
	                .setExpiration(generateExpirationDate())
	                .signWith(SignatureAlgorithm.HS512, secret)
	                .compact();
	}

	@Override
	public Boolean validateToken(String jwt, UserDetails userDetails) {
	      UserBean user = (UserBean) userDetails;
	      final String username = getUsernameFromToken(jwt);
	      return ( username.equalsIgnoreCase(user.getUsername())
	               && !isTokenExpired(jwt)
	               );
	}

}
