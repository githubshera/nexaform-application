package com.sheroo.nexaform.application.controller;

import com.sheroo.nexaform.application.entity.Task;
import com.sheroo.nexaform.application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //get all task
    @GetMapping
    public List<Task> getAllTasks() {
       return taskService.getAllTasks();
    }

    //get by id task
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
       Optional<Task> task = taskService.getTaskById(id);

       if (task.isPresent()) {
           return ResponseEntity.ok(task.get());
       }
       return ResponseEntity.notFound().build();
    }

    //create or post

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
       Task save= taskService.saveTask(task);
       return ResponseEntity.ok(save);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> taskOptional = taskService.getTaskById(id);
        if(taskOptional.isPresent()) {
            Task updateTasks = taskOptional.get();
            updateTasks.setDescription(task.getDescription());
            updateTasks.setName(task.getName());
            updateTasks.setDueDate(task.getDueDate());
            updateTasks.setCompleted(task.getCompleted());
            return ResponseEntity.ok(taskService.saveTask(updateTasks));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
