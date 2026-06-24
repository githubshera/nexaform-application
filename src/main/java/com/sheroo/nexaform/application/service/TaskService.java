package com.sheroo.nexaform.application.service;

import com.sheroo.nexaform.application.entity.Project;
import com.sheroo.nexaform.application.entity.Task;
import com.sheroo.nexaform.application.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //get all list of project logic
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    //by id
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    //save projects
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    //delete by id
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
