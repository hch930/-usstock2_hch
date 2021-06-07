package org.hch.auth.repository;

import org.hch.auth.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	public Member findByUsername(String username);
	public Member findByEmail(String email);
}
