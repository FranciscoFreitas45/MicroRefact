package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.sda.inTeams.service.*;
 import java.util.*;
 import com.sda.inTeams.model.Project.*;
  import com.sda.inTeams.exception.InvalidOperation;

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
  try {
  return projectservice.getAllProjectsOfTeam(teamId);
  }catch( InvalidOperation e){
      return null;
  } 
}


}