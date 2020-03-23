package fi.aktia.demo.jwtapp.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

@Entity
@Table(name= "aktia_permission")
public class PermissionBean {

	@Id
	@Column(name= "id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "aktia_permission_seq")
	@SequenceGenerator(name= "aktia_permission_seq", sequenceName = "aktia_permission_seq", allocationSize = 1)
	private int id;
	
	@ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
	private List<UserBean> users;
	
	@ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<RoleBean> roles;

	public PermissionBean() {
		super();
	}

	public PermissionBean(int id, List<UserBean> users) {
		super();
		this.id = id;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<UserBean> getUsers() {
		return users;
	}

	public void setUsers(List<UserBean> users) {
		this.users = users;
	}

	public List<RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}
	
	
}
