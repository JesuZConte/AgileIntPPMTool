package com.jesuzconte.ppmtool.web;

import com.jesuzconte.ppmtool.domain.Project;
import com.jesuzconte.ppmtool.services.ProjectService;
import com.jesuzconte.ppmtool.util.MapValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationError mapValidationError;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<Map<String, String>> errorMap = mapValidationError.getErrorsMap(result);
        if (errorMap != null) return errorMap;

        Project saveOrUpdateProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(saveOrUpdateProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<>("Project with ID: " + projectId + " was deleted.", HttpStatus.OK );
    }

}
