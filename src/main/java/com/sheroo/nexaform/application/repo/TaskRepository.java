package com.sheroo.nexaform.application.repo;

import com.sheroo.nexaform.application.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
