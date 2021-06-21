package org.hch.security.auth.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hch.security.auth.model.Role;
import org.hch.security.auth.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource(name = "userServiceImpl")
	private UserService userService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("[hch] call the loadUserByUsername()");
		//< get the user information
		User user = null;
		try {
			user = userService.findUserByUsername(username);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new UsernameNotFoundException(e.getMessage());
		}
		
		//< set the user authorities
		return buildUserForAuthentication(user, getUserAuthority(user.getRoles()));
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRole) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for(Role role : userRole) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
		
		return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
}
