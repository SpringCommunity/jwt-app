package fi.aktia.demo.jwtapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.aktia.demo.jwtapp.bean.RoleBean;
import fi.aktia.demo.jwtapp.repository.RoleRepository;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/api/v1/")
public class RoleController {
	@Autowired
	private RoleRepository roleRepo;
	
	@RequestMapping(value= "roles", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public List<RoleBean> findAllRoles(){
		return roleRepo.findAll();
	}
}
