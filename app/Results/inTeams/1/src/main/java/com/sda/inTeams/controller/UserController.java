package com.sda.inTeams.controller;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.model.User.AccountRole;
import com.sda.inTeams.DTO.User;
import com.sda.inTeams.repository.AccountRoleRepository;
import com.sda.inTeams.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import com.sda.inTeams.Interface.TeamService;
import com.sda.inTeams.Interface.CommentService;
import com.sda.inTeams.Interface.TaskService;
@Service
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

 private  UserService userService;

 private  TeamService teamService;

 private  CommentService commentService;

 private  TaskService taskService;

 private  AccountRoleRepository accountRoleRepository;

 private  AuthorizationService authorizationService;


@GetMapping("/myaccount")
public String getMyAccountPage(Model model,Principal principal){
    try {
        User user = authorizationService.getUserCredentials(principal).orElseThrow();
        return getUser(model, principal, user.getId());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "redirect:/";
}


@GetMapping("/{id}")
public String getUser(Model model,Principal principal,long userId){
    try {
        User user = authorizationService.getUserCredentials(principal).orElseThrow();
        if (authorizationService.isUserEligibleToSeeUserDetails(principal, user)) {
            model.addAttribute("userDetails", userService.getByIdOrThrow(userId));
            model.addAttribute("teamsAssignedTo", teamService.getTeamsContainingMember(userId));
            model.addAttribute("teamsOwnedBy", teamService.getTeamsOwnedBy(userId));
            model.addAttribute("commentsCreated", commentService.getAllUserComments(userId));
            model.addAttribute("tasksResponsibleFor", taskService.getAllTasksByUserResponsibleFor(user));
            return "user-details";
        } else {
        // unauthorized access
        }
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
    }
    return "redirect:/";
}


@GetMapping("/edit/{id}")
public String editUser(Model model,long userId){
    try {
        User userToEdit = userService.getByIdOrThrow(userId);
        model.addAttribute("newUser", userToEdit);
        AccountRole adminRole = accountRoleRepository.findByName("ROLE_ADMIN").orElseThrow();
        model.addAttribute("isAdmin", userToEdit.getRoles().contains(adminRole));
        return "user-edit-form";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


@GetMapping("/delete/{id}")
public String deleteUser(long userId){
    try {
        userService.delete(userId);
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
    }
    return "redirect:/";
}


@PostMapping("/save")
public String saveEditedUser(Model model,User user,boolean isAdmin){
    user.setRoles(new HashSet<>(List.of(accountRoleRepository.findByName(isAdmin ? "ROLE_ADMIN" : "ROLE_USER").orElseThrow())));
    user = userService.saveToDatabase(user);
    return "redirect:/user/" + user.getId();
}


}