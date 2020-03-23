package fi.aktia.demo.jwtapp.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
	@JsonIgnore
	private int id;
	
	@ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserBean> users;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_role_id", referencedColumnName = "id")
	private RoleBean role;

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

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}
	
}
