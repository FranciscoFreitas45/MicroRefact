package com.sda.inTeams.service;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Project.ProjectStatus;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.DTO.*;
import com.sda.inTeams.repository.ProjectRepository;
import com.sda.inTeams.repository.TaskRepository;
import com.sda.inTeams.Interface.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sda.inTeams.Interface.TeamRepository;

@Service
@RequiredArgsConstructor
public class ProjectService implements DatabaseManageable<Project> {

    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;
    private final TaskRepository taskRepository;

    private final TaskService taskService;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<Project> getAllProjectsOfTeam(long teamId) throws InvalidOperation {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new InvalidOperation("Team id:" + teamId + " not found!"));
        return projectRepository.findAllByProjectOwner(team);
    }

    public Optional<Project> getById(long projectId) {
        return projectRepository.findById(projectId);
    }

    public Project getByIdOrThrow(long projectId) throws InvalidOperation {
        return getById(projectId).orElseThrow(
                () -> new InvalidOperation("Project id:" + projectId + " not found!"));
    }

    public Project add(Project project) throws InvalidOperation {
        if (!Objects.isNull(project)) {
            return saveToDatabase(project);
        } else {
            throw new InvalidOperation("Cannot add project - Object is null!");
        }
    }

    public void delete(long projectId) throws InvalidOperation {
        Project projectToDelete = getByIdOrThrow(projectId);
        Team team = teamRepository.findByProjectsContaining(projectToDelete).orElseThrow(() -> new InvalidOperation("Team not found!"));
        List<Project> teamProjects = projectRepository.findAllByProjectOwner(team);
        teamProjects.remove(projectToDelete);
        team.setProjects(new HashSet<>(teamProjects));
        projectToDelete.setProjectOwner(null);
        teamRepository.save(team);
        projectToDelete = saveToDatabase(projectToDelete);
        projectRepository.delete(projectToDelete);
    }

    public Project changeStatus(long projectId, ProjectStatus status) throws InvalidOperation {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new InvalidOperation("Project id:" + projectId + " not found!"));
        project.setStatus(status);
        return saveToDatabase(project);
    }

    public void addTaskToProject(long projectId, long taskId) throws InvalidOperation {
        Project project = getByIdOrThrow(projectId);
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new InvalidOperation("Task id:" + taskId + " not found!"));
        if (!project.getTasks().contains(task)) {
            project.getTasks().add(task);
            saveToDatabase(project);
            task.setProject(project);
            taskRepository.save(task);
        } else {
            throw new InvalidOperation("Cannot add task id:" + taskId + " to project id:" + projectId + " - Task already assigned to this Project");
        }
    }

    public Project removeTaskFromProject(long projectId, long taskId) throws InvalidOperation {
        Task taskToRemove = taskRepository.findById(taskId).orElseThrow(() -> new InvalidOperation("Task id:" + taskId + " not found!"));
        Project projectToRemoveFrom = projectRepository.findById(projectId).orElseThrow(() -> new InvalidOperation("Project id:" + projectId + " not found!"));

        if (projectToRemoveFrom.getTasks().contains(taskToRemove)) {
            taskRepository.delete(taskToRemove);
            return saveToDatabase(projectToRemoveFrom);
        } else {
            throw new InvalidOperation("Cannot remove task id:" + taskId + " from project id:" + projectId + " - Task is not assigned to this Project!");
        }

    }

    public Project saveToDatabase(Project project) {
        return projectRepository.save(project);
    }

    public List<User> getAllMembers(Project project) {
        Team team = teamRepository.findByProjectsContaining(project).orElseThrow();
        return new ArrayList<>(team.getMembers());
    }
}
