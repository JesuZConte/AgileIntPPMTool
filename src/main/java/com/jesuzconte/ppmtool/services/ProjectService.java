package com.jesuzconte.ppmtool.services;

import com.jesuzconte.ppmtool.domain.Project;
import com.jesuzconte.ppmtool.exceptions.ProjectIdException;
import com.jesuzconte.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists. ");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (null == project) {
            throw new ProjectIdException("Project ID: " + projectId.toUpperCase() + " does not exist");
        }

        return project;
    }

}
