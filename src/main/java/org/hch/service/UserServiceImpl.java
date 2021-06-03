package org.hch.service;

import java.util.Arrays;
import java.util.HashSet;

import org.hch.domain.Member;
import org.hch.domain.Role;
import org.hch.eo.ERole;
import org.hch.repository.MemberRepository;
import org.hch.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService{
	private MemberRepository memberRepository;
	private RoleRepository roleRepository;
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
