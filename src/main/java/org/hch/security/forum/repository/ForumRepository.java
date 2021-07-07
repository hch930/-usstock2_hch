package org.hch.security.forum.repository;

import org.hch.security.forum.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Long>{

}
