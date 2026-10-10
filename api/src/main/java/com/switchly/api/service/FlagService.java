package com.switchly.api.service;

import com.switchly.api.exception.ConflictException;
import com.switchly.api.exception.NotFoundException;
import com.switchly.api.model.Flag;
import com.switchly.api.model.Project;
import com.switchly.api.repository.FlagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FlagService {

    private final ProjectService projectService;
    private final FlagRepository flagRepository;

    public FlagService(ProjectService projectService, FlagRepository flagRepository) {
        this.projectService = projectService;
        this.flagRepository = flagRepository;
    }

    public Flag create(UUID projectId, String key, String name, String description) {
        Project project = projectService.getById(projectId);  // 404 if it doesn't exist

        if (flagRepository.existsByProjectIdAndKey(projectId, key)) {
            throw new ConflictException("A flag with key '" + key + "' already exists in this project");
        }

        Flag flag = new Flag(UUID.randomUUID(), project.getOrganizationId(), project.getId(), key, name, false, description);
        return flagRepository.save(flag);
    }

    public Flag getById(UUID id) {
        return flagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flag " + id + " not found"));
    }

    public List<Flag> getAllForProject(UUID projectId) {
        projectService.getById(projectId);  // 404 if it doesn't exist
        return flagRepository.findByProjectId(projectId);
    }

    public Flag setEnabled(UUID flagId, boolean enabled) {
        Flag flag = getById(flagId);
        flag.setEnabled(enabled);
        return flagRepository.save(flag);
    }

    public void deleteFlag(UUID flagId) {
        getById(flagId); // if flag not found 404 exception

        flagRepository.deleteById(flagId);
    }
}