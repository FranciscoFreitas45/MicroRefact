package com.sda.inTeams.controller;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.service.ProjectService;
import com.sda.inTeams.service.TeamService;
import com.sda.inTeams.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sda.inTeams.Interface.UserService;
import com.sda.inTeams.Interface.ProjectService;
@Controller
@RequestMapping("/myTeam/")
@RequiredArgsConstructor
public class MyViewController {

 private  TeamService teamService;

 private  UserService userService;

 private  ProjectService projectService;


@RequestMapping("/{id}")
public String getMyTeamView(Model model,long teamId){
    try {
        model.addAttribute("team", teamService.getByIdOrThrow(teamId));
        model.addAttribute("teamMembers", userService.getAllMembersOfTeam(teamId));
        model.addAttribute("teamProjects", projectService.getAllProjectsOfTeam(teamId));
        return "my-team-view";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


}