package org.hch.auth.service;

import org.hch.auth.domain.Member;

public interface UserService {
	public Member getUserByEmail(String email) throws Exception;
	public Member getUserByUsername(String username) throws Exception;
	public Member setUser(Member user) throws Exception;
}
