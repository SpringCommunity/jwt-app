package fi.aktia.demo.jwtapp.config.auth.service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.aktia.demo.jwtapp.bean.UserBean;
import fi.aktia.demo.jwtapp.config.auth.bean.AuthUserDetailsBean;
import fi.aktia.demo.jwtapp.repository.UserRepository;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService {
	private Logger logger = Logger.getLogger(AuthUserDetailsServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        UserBean user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	logger.info("Username found from database: "+ user.getUsername());
            return new AuthUserDetailsBean(user);
        }
    }

}
