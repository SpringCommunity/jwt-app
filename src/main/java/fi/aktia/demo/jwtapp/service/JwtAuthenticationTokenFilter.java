package fi.aktia.demo.jwtapp.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Value("${jwt.header}")
    private String header;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
    private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authToken = request.getHeader(this.header);
		String username = jwtService.getUsernameFromToken(authToken);
		logger.info("Checking authentication from username: "+ username);
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(jwtService.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authenticated " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}

}
