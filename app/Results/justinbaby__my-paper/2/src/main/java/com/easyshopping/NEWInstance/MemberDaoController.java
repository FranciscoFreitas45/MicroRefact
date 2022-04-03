package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberDaoController {

 private MemberDao memberdao;


@GetMapping
("/lock")
public Object lock(@RequestParam(name = "Object") Object Object){
  return memberdao.lock(Object);
}


@GetMapping
("/merge")
public Object merge(@RequestParam(name = "Object") Object Object){
  return memberdao.merge(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return memberdao.find(Object);
}


}