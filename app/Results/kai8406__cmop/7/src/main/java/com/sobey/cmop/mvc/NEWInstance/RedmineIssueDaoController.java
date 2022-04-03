package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RedmineIssueDaoController {

 private RedmineIssueDao redmineissuedao;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return redmineissuedao.findOne(Object);
}


@GetMapping
("/findByIssueId")
public RedmineIssue findByIssueId(@RequestParam(name = "issueId") Integer issueId){
  return redmineissuedao.findByIssueId(issueId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return redmineissuedao.save(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return redmineissuedao.findAll(Object);
}


}