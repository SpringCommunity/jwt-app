package fi.aktia.demo.jwtapp.config.auth.bean;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fi.aktia.demo.jwtapp.bean.PermissionBean;
import fi.aktia.demo.jwtapp.bean.UserBean;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

public class AuthUserDetailsBean implements UserDetails {

	private static final long serialVersionUID = 3215947079138301869L;

	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Collection<? extends GrantedAuthority> permissions;


    private static List<GrantedAuthority> mapToGrantedAuthorities(List<PermissionBean> permissions) {
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getRole().getRoleName()))
                .collect(Collectors.toList());
    }
    
	public AuthUserDetailsBean(UserBean user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.permissions = mapToGrantedAuthorities(user.getPermissions());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissions;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	

}
