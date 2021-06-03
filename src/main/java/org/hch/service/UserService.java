package org.hch.service;

import org.hch.domain.Member;

public interface UserService {
	public Member getUserByEmail(String email) throws Exception;
	public Member getUserByUsername(String username) throws Exception;
	public Member setUser(Member user) throws Exception;
}
