package fi.aktia.demo.jwtapp.config.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.aktia.demo.jwtapp.config.auth.bean.AuthRequestBean;
import fi.aktia.demo.jwtapp.config.auth.bean.AuthResponseBean;
import fi.aktia.demo.jwtapp.config.auth.bean.AuthUserDetailsBean;
import fi.aktia.demo.jwtapp.config.auth.service.AuthServiceImpl;
import fi.aktia.demo.jwtapp.config.auth.service.AuthUserDetailsServiceImpl;


/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

@RestController
public class AuthController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthUserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthServiceImpl authService;

	@RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestBean authRequest)
			throws AuthenticationException {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final AuthUserDetailsBean userDetails = (AuthUserDetailsBean) userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String token = authService.generateToken(userDetails.getUsername());

		return ResponseEntity.ok(new AuthResponseBean(token));
	}

	@RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = authService.getUsernameFromToken(token);
		final AuthUserDetailsBean userDetails = (AuthUserDetailsBean) userDetailsService.loadUserByUsername(username);

		if (authService.canTokenBeRefreshed(token, userDetails)) {
			String refreshedToken = authService.refreshToken(token);
			return ResponseEntity.ok(new AuthResponseBean(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
