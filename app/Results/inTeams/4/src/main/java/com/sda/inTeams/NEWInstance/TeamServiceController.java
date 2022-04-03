package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeamServiceController {

 private TeamService teamservice;


@GetMapping
("/getByIdOrThrow")
public Team getByIdOrThrow(@RequestParam(name = "teamId") long teamId){
  return teamservice.getByIdOrThrow(teamId);
}


@PutMapping
("/removeUserFromTeam")
public void removeUserFromTeam(@RequestParam(name = "teamId") long teamId,@RequestParam(name = "userId") long userId){
teamservice.removeUserFromTeam(teamId,userId);
}


@GetMapping
("/getTeamsContainingMember")
public List<Team> getTeamsContainingMember(@RequestParam(name = "userId") long userId){
  return teamservice.getTeamsContainingMember(userId);
}


@GetMapping
("/getTeamsOwnedBy")
public List<Team> getTeamsOwnedBy(@RequestParam(name = "userId") long userId){
  return teamservice.getTeamsOwnedBy(userId);
}


@GetMapping
("/getAll")
public List<Team> getAll(){
  return teamservice.getAll();
}


}