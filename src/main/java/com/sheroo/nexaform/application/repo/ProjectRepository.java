package com.sheroo.nexaform.application.repo;

import com.sheroo.nexaform.application.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
