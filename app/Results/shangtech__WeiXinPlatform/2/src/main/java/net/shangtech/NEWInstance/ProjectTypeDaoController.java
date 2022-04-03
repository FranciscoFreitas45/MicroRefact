package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProjectTypeDaoController {

 private ProjectTypeDao projecttypedao;


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return projecttypedao.insert(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return projecttypedao.find(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return projecttypedao.update(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return projecttypedao.delete(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return projecttypedao.count(Object);
}


}