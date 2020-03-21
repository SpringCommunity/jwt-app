package fi.aktia.demo.jwtapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.aktia.demo.jwtapp.bean.PermissionBean;
import fi.aktia.demo.jwtapp.repository.PermissionRepository;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/api/v1/")
public class PermissionController {
    
	@Autowired
	private PermissionRepository permissionRepo;
	
	@RequestMapping(value= "permissions", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public List<PermissionBean> findAllPermissions(){
		return permissionRepo.findAll();
	}
	
}
