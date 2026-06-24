package com.sheroo.nexaform.application.service;

import com.sheroo.nexaform.application.entity.Project;
import com.sheroo.nexaform.application.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //get all list of project logic
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    //by id
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    //save projects
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    //delete by id
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
