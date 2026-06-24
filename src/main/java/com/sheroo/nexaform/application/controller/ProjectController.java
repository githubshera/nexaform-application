package com.sheroo.nexaform.application.controller;

import com.sheroo.nexaform.application.entity.Project;
import com.sheroo.nexaform.application.entity.Task;
import com.sheroo.nexaform.application.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
       return projectService.getAllProjects();
    }
    // read or get
    @GetMapping("/{id}")
    public ResponseEntity<Project> getAllProjects(@PathVariable Long id) {
         Optional<Project> project = projectService.getProjectById(id);
         if(project.isPresent()) {
             return ResponseEntity.ok(project.get());
         } else {
             return ResponseEntity.notFound().build();
         }
    }
    //create
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project save = projectService.saveProject(project);
        return ResponseEntity.ok(save);
    }
   //update

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProjects(@PathVariable Long id, @RequestBody Project project) {
        Optional<Project> getProject = projectService.getProjectById(id);

        if(getProject.isPresent()) {
            Project updateProject = getProject.get();
            updateProject.setDescription(updateProject.getDescription());
            updateProject.setName(updateProject.getName());
            //-but for the task we have to do different
            updateProject.getTasks().clear(); // first we clear all the task
            //then re-add the tasks, ensuring that each task is associated with the project
            for(Task task: project.getTasks()) {
                updateProject.addTask(task);
            }
            return ResponseEntity.ok(projectService.saveProject(updateProject));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
       projectService.deleteById(id);
       return ResponseEntity.notFound().build();
    }
}
