package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProjectRepositoryController {

 private ProjectRepository projectrepository;


@GetMapping
("/findByTasksContaining")
public Optional<Project> findByTasksContaining(@RequestParam(name = "task") Task task){
  return projectrepository.findByTasksContaining(task);
}


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


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return projectrepository.saveAll(Object);
}


@GetMapping
("/findAllByProjectOwner")
public List<Project> findAllByProjectOwner(@RequestParam(name = "team") Team team){
  return projectrepository.findAllByProjectOwner(team);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return projectrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return projectrepository.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return projectrepository.delete(Object);
}


}