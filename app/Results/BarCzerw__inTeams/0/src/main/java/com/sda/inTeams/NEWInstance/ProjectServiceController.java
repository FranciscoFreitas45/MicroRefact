package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProjectServiceController {

 private ProjectService projectservice;


@GetMapping
("/getAll")
public List<Project> getAll(){
  return projectservice.getAll();
}


@GetMapping
("/getAllProjectsOfTeam")
public List<Project> getAllProjectsOfTeam(@RequestParam(name = "teamId") long teamId){
  return projectservice.getAllProjectsOfTeam(teamId);
}


}