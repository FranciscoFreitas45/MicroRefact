package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SubProjectDaoController {

 private SubProjectDao subprojectdao;


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return subprojectdao.insert(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return subprojectdao.find(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return subprojectdao.update(Object);
}


}