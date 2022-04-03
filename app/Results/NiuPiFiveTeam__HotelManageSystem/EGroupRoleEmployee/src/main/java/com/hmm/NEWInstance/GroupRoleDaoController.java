package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GroupRoleDaoController {

 private GroupRoleDao grouproledao;


@GetMapping
("/findByGroupId")
public GroupRole findByGroupId(@RequestParam(name = "groupId") String groupId){
  return grouproledao.findByGroupId(groupId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return grouproledao.save(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return grouproledao.findById(Object);
}


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return grouproledao.existsById(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return grouproledao.deleteById(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return grouproledao.findAll(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return grouproledao.count(Object);
}


@GetMapping
("/findAllById")
public Object findAllById(@RequestParam(name = "Object") Object Object){
  return grouproledao.findAllById(Object);
}


@GetMapping
("/deleteAll")
public Object deleteAll(@RequestParam(name = "Object") Object Object){
  return grouproledao.deleteAll(Object);
}


@GetMapping
("/findByGroupName")
public GroupRole findByGroupName(@RequestParam(name = "groupName") String groupName){
  return grouproledao.findByGroupName(groupName);
}


}