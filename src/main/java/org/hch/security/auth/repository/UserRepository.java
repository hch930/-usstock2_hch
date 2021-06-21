package org.hch.security.auth.repository;

import org.hch.security.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
