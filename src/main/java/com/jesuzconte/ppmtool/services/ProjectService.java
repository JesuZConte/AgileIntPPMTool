package com.jesuzconte.ppmtool.services;

import com.jesuzconte.ppmtool.domain.Project;
import com.jesuzconte.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        return projectRepository.save(project);
    }

}
