package com.sda.inTeams.controller;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.model.Team.Team;
import com.sda.inTeams.model.User.User;
import com.sda.inTeams.service.AuthorizationService;
import com.sda.inTeams.service.ProjectService;
import com.sda.inTeams.service.TeamService;
import com.sda.inTeams.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import com.sda.inTeams.Interface.ProjectService;
import com.sda.inTeams.Interface.UserService;
import com.sda.inTeams.Interface.AuthorizationService;
@Service
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

 private  TeamService teamService;

 private  ProjectService projectService;

 private  UserService userService;

 private  AuthorizationService authorizationService;


@GetMapping("/add")
public String addTeamForm(Model model,long userId){
    try {
        User projectOwner = userService.getByIdOrThrow(userId);
        model.addAttribute("newTeam", new Team());
        model.addAttribute("ownerId", userId);
        return "team-add-form";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


@GetMapping("/delete/{id}")
public String deleteTeam(long teamId){
    try {
        Team teamToDelete = teamService.getByIdOrThrow(teamId);
        teamService.delete(teamId);
        return "redirect:/team";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


@GetMapping("/{id}")
public String getTeam(Model model,Principal principal,long teamId){
    try {
        Team team = teamService.getByIdOrThrow(teamId);
        if (authorizationService.isUserEligibleToSeeTeamDetails(principal, team)) {
            model.addAttribute("teamDetails", teamService.getByIdOrThrow(teamId));
            model.addAttribute("teamProjects", projectService.getAllProjectsOfTeam(teamId));
            model.addAttribute("teamMembers", userService.getAllMembersOfTeam(teamId));
            model.addAttribute("canManageTeam", authorizationService.isUserEligibleToManageTeam(principal, team));
            model.addAttribute("userId", authorizationService.getUserCredentials(principal).orElseThrow().getId());
            return "team-details";
        } else {
        // unauthorized access
        }
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
    }
    return "redirect:/";
}


@PostMapping("/add")
public String addTeam(Team team,long userId){
    try {
        Team addedTeam = teamService.createWithOwner(team, userId);
        return "redirect:/team/" + addedTeam.getId();
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


@GetMapping("/edit/{id}")
public String editTeam(Model model,long teamId){
    try {
        Team teamToEdit = teamService.getByIdOrThrow(teamId);
        model.addAttribute("newTeam", teamToEdit);
        model.addAttribute("ownerId", teamToEdit.getTeamOwner().getId());
        return "team-add-form";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


@PostMapping("/invite")
public String inviteUserToTeam(Model model,Principal principal,long teamId,String invitationCode){
    try {
        User userToInvite = userService.getByInvitationCodeOrThrow(invitationCode);
        Team team = teamService.getByIdOrThrow(teamId);
        teamService.addUserToTeam(team, userToInvite);
        return "redirect:/team/" + teamId;
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


@GetMapping("")
public String getUserTeams(Model model,Principal principal){
    try {
        User user = authorizationService.getUserCredentials(principal).orElseThrow();
        model.addAttribute("teamList", teamService.getTeamsContainingMember(user.getId()));
        model.addAttribute("userId", user.getId());
        return "user-team-list";
    } catch (InvalidOperation operation) {
        operation.printStackTrace();
        return "redirect:/";
    }
}


@GetMapping("/removeUser")
public String removeUserFromTeam(long teamId,long userId){
    try {
        teamService.removeUserFromTeam(teamId, userId);
        return "redirect:/team/" + teamId;
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


}