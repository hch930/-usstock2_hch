package org.hch.security.auth.repository;

import org.hch.security.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);
}
