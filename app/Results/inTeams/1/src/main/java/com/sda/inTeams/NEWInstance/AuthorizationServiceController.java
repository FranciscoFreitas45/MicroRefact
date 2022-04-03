package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.sda.inTeams.DTO.*;
import java.util.*;
import com.sda.inTeams.service.AuthorizationService;
import java.security.Principal;

@RestController
@CrossOrigin
public class AuthorizationServiceController {

 private AuthorizationService authorizationservice;


@GetMapping
("/getUserCredentials")
public Optional<User> getUserCredentials(@RequestParam(name = "principal") Principal principal){
  return authorizationservice.getUserCredentials(principal);
}


@GetMapping
("/isUserEligibleToEditComment")
public boolean isUserEligibleToEditComment(@RequestParam(name = "principal") Principal principal,@RequestParam(name = "comment") Comment comment){
  return authorizationservice.isUserEligibleToEditComment(principal,comment);
}


@GetMapping
("/isUserEligibleToDeleteComment")
public boolean isUserEligibleToDeleteComment(@RequestParam(name = "principal") Principal principal,@RequestParam(name = "comment") Comment comment){
  return authorizationservice.isUserEligibleToDeleteComment(principal,comment);
}


@GetMapping
("/isUserEligibleToSeeTaskDetails")
public boolean isUserEligibleToSeeTaskDetails(@RequestParam(name = "principal") Principal principal,@RequestParam(name = "task") Task task){
  return authorizationservice.isUserEligibleToSeeTaskDetails(principal,task);
}


@GetMapping
("/isUserAdmin")
public boolean isUserAdmin(@RequestParam(name = "principal") Principal principal){
  return authorizationservice.isUserAdmin(principal);
}


@GetMapping
("/isUserEligibleToSeeProjectDetails")
public boolean isUserEligibleToSeeProjectDetails(@RequestParam(name = "principal") Principal principal,@RequestParam(name = "project") Project project){
  return authorizationservice.isUserEligibleToSeeProjectDetails(principal,project);
}


@GetMapping
("/isUserEligibleToSeeTeamDetails")
public boolean isUserEligibleToSeeTeamDetails(@RequestParam(name = "principal") Principal principal,@RequestParam(name = "team") Team team){
  return authorizationservice.isUserEligibleToSeeTeamDetails(principal,team);
}


@GetMapping
("/isUserEligibleToManageTeam")
public Object isUserEligibleToManageTeam(@RequestParam(name = "principal") Principal principal,@RequestParam(name = "team") Team team){
  return authorizationservice.isUserEligibleToManageTeam(principal,team);
}


}