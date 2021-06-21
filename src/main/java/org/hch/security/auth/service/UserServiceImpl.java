package org.hch.security.auth.service;

import java.util.Arrays;
import java.util.HashSet;

import org.hch.security.auth.model.ERole;
import org.hch.security.auth.model.Role;
import org.hch.security.auth.model.User;
import org.hch.security.auth.repository.RoleRepository;
import org.hch.security.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired(required = false)
    private UserRepository userRepository;
    @Autowired(required = false)
    private RoleRepository roleRepository;
    @Autowired(required = false)
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public User findUserByUsername(String username) {
    	return userRepository.findByUsername(username);
    }
    
    @Override
    public void saveUser(User user) {
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	user.setEnabled(true);
    	Role userRole = null;
    	if(user.getUsername().equals("admin")) {
			userRole = roleRepository.findByRole(ERole.ADMIN.getValue());
		}
		else if(user.getUsername().equals("user")) {
			userRole = roleRepository.findByRole(ERole.MANAGER.getValue());
		}
		else {
			userRole = roleRepository.findByRole(ERole.GUEST.getValue());
		}
    	user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    	userRepository.save(user);
    }	
}
