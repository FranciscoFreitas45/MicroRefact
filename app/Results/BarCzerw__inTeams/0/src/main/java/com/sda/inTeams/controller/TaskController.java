package com.sda.inTeams.controller;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Comment.CommentType;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.Task.TaskStatus;
import com.sda.inTeams.model.User.User;
import com.sda.inTeams.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.sda.inTeams.Interface.UserService;
import com.sda.inTeams.Interface.AuthorizationService;
@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

 private  TaskService taskService;

 private  ProjectService projectService;

 private  CommentService commentService;

 private  UserService userService;

 private  AuthorizationService authorizationService;


@GetMapping("/edit/{id}")
public String editTask(Model model,long taskId){
    try {
        Task taskToEdit = taskService.getByIdOrThrow(taskId);
        model.addAttribute("newTask", taskToEdit);
        model.addAttribute("ownerId", taskToEdit.getProject().getId());
        model.addAttribute("responsibleId", taskToEdit.getUserResponsible().getId());
        model.addAttribute("statuses", new ArrayList<>(List.of(TaskStatus.values())));
        model.addAttribute("teamMembers", projectService.getAllMembers(taskToEdit.getProject()));
        return "task-add-form";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/task/all";
    }
}


@GetMapping("/delete/{id}")
public String deleteTask(long taskId){
    try {
        Task taskToDelete = taskService.getByIdOrThrow(taskId);
        long projectId = taskToDelete.getProject().getId();
        taskService.delete(taskId);
        return "redirect:/project/" + projectId;
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/task/all";
    }
}


@GetMapping("/{id}")
public String getTask(Model model,Principal principal,long taskId){
    try {
        Task task = taskService.getByIdOrThrow(taskId);
        if (authorizationService.isUserEligibleToSeeTaskDetails(principal, task)) {
            model.addAttribute("taskDetails", task);
            model.addAttribute("taskComments", commentService.getAllByTask(taskId));
            model.addAttribute("newComment", new Comment());
            model.addAttribute("ownerId", taskId);
            model.addAttribute("commentTypes", CommentType.values());
            model.addAttribute("creatorId", authorizationService.getUserCredentials(principal).get().getId());
            model.addAttribute("isAdmin", authorizationService.isUserAdmin(principal));
            return "task-details";
        } else {
        // unauthorized access
        }
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
    }
    return "redirect:/";
}


@GetMapping("/add")
public String addTaskForm(Model model,Principal principal,long projectId){
    try {
        Project taskOwner = projectService.getByIdOrThrow(projectId);
        model.addAttribute("newTask", new Task());
        model.addAttribute("ownerId", projectId);
        model.addAttribute("responsibleId", -1);
        model.addAttribute("statuses", new ArrayList<>(List.of(TaskStatus.values())));
        model.addAttribute("teamMembers", projectService.getAllMembers(taskOwner));
        return "task-add-form";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/project/all";
    }
}


@PostMapping("/add")
public String addTask(Task task,long ownerId,long responsibleId){
    try {
        // task.setStatus(task.getStatus());
        task.setProject(projectService.getByIdOrThrow(ownerId));
        Task addedTask = taskService.add(task);
        taskService.setUserResponsible(task.getId(), responsibleId);
        return "redirect:/task/" + addedTask.getId();
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/task/all";
    }
}


}