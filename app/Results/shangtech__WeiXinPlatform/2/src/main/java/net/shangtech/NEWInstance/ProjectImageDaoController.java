package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProjectImageDaoController {

 private ProjectImageDao projectimagedao;


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return projectimagedao.insert(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return projectimagedao.find(Object);
}


}