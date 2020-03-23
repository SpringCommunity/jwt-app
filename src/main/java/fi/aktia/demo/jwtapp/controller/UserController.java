package fi.aktia.demo.jwtapp.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.aktia.demo.jwtapp.bean.UserBean;
import fi.aktia.demo.jwtapp.config.auth.service.AuthServiceImpl;
import fi.aktia.demo.jwtapp.repository.UserRepository;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	@Value("${jwt.admin}")
	private String admin;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthServiceImpl authService;
	
	@RequestMapping(value= "users", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserBean> findAllUsers(){
		return userRepo.findAll();
	}
	
	@RequestMapping(value= "user/{id}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> findById(@PathVariable int id, @RequestHeader("${jwt.header}") String token) {
		logger.info("::UserController::findById:: "+token);
		String username = authService.getUsernameFromToken(token);
		UserBean user = userRepo.findById(id).get();
		if(username.equalsIgnoreCase(user.getUsername()) || username.equalsIgnoreCase(admin)) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
}
