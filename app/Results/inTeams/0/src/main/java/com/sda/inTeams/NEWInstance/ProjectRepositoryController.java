package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.sda.inTeams.repository.*;
 import java.util.*;
 import com.sda.inTeams.model.Task.*;
import com.sda.inTeams.model.Project.*;
import com.sda.inTeams.DTO.*;
 
 
@RestController
@CrossOrigin
public class ProjectRepositoryController {

 private ProjectRepository projectrepository;


@GetMapping
("/findByTasksContaining")
public Optional<Project> findByTasksContaining(@RequestParam(name = "task") Task task){
  return projectrepository.findByTasksContaining(task);
}

/*
@GetMapping
("/orElseThrow")
public Object orElseThrow(@RequestParam(name = "Object") Object Object){
  return projectrepository.orElseThrow(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return projectrepository.count(Object);
}
*/

@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return projectrepository.saveAll((List<Project>)Object);
}


@GetMapping
("/findAllByProjectOwner")
public List<Project> findAllByProjectOwner(@RequestParam(name = "team") Team team){
  return projectrepository.findAllByProjectOwner(team);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return projectrepository.findById((long)Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return projectrepository.save((Project)Object);
}


@GetMapping
("/delete")
public void delete(@RequestParam(name = "Object") Object Object){
  projectrepository.delete((Project)Object);
  return ;
}


}