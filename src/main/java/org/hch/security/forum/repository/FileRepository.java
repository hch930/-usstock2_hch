package org.hch.security.forum.repository;

import org.hch.security.forum.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
