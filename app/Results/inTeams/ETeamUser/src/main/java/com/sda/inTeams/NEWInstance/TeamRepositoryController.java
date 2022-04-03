package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeamRepositoryController {

 private TeamRepository teamrepository;


@GetMapping
("/findByProjectsContaining")
public Optional<Team> findByProjectsContaining(@RequestParam(name = "project") Project project){
  return teamrepository.findByProjectsContaining(project);
}


@GetMapping
("/orElseThrow")
public Object orElseThrow(@RequestParam(name = "Object") Object Object){
  return teamrepository.orElseThrow(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return teamrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return teamrepository.save(Object);
}


@GetMapping
("/findAllByTeamOwner")
public List<Team> findAllByTeamOwner(@RequestParam(name = "user") User user){
  return teamrepository.findAllByTeamOwner(user);
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return teamrepository.isEmpty(Object);
}


@GetMapping
("/findAllByMembersContaining")
public List<Team> findAllByMembersContaining(@RequestParam(name = "member") User member){
  return teamrepository.findAllByMembersContaining(member);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return teamrepository.count(Object);
}


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return teamrepository.saveAll(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return teamrepository.findAll(Object);
}


@GetMapping
("/findByName")
public Optional<Team> findByName(@RequestParam(name = "name") String name){
  return teamrepository.findByName(name);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return teamrepository.delete(Object);
}


}