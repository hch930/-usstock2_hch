package org.hch.auth.service;

import java.util.Arrays;
import java.util.HashSet;

import org.hch.auth.domain.Member;
import org.hch.auth.domain.Role;
import org.hch.auth.eo.ERole;
import org.hch.auth.repository.MemberRepository;
import org.hch.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = "org.hch.repository")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	public Member getUserByEmail(String email) throws Exception {
		return memberRepository.findByEmail(email);
	}
	@Override
	public Member getUserByUsername(String username) throws Exception {
		return memberRepository.findByUsername(username);
	}
	@Override
	public Member setUser(Member user) throws Exception {
		user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		Role userRole = null;
		if(user.getUsername().equals("admin")) {
			userRole = roleRepository.findByRole(ERole.ADMIN.getValue());
		}
		else if(user.getUsername().equals("user")) {
			userRole = roleRepository.findByRole(ERole.MANAGER.getValue());
		}else {
			userRole = roleRepository.findByRole(ERole.GUEST.getValue());
		}
		
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return memberRepository.save(user);
	}
}
