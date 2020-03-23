package fi.aktia.demo.jwtapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.aktia.demo.jwtapp.bean.UserBean;
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
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value= "users", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public List<UserBean> findAllUsers(){
		return userRepo.findAll();
	}
	
	@RequestMapping(value= "user/{id}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public UserBean findById(@PathVariable int id) {
		return userRepo.findById(id).get();
	}
}
