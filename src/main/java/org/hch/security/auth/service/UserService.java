package org.hch.security.auth.service;

import org.hch.security.auth.model.User;

public interface UserService {
	public User findUserByUsername(String username);
	public void saveUser(User user);
}
