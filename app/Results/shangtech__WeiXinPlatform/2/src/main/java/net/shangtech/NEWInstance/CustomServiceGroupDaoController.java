package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CustomServiceGroupDaoController {

 private CustomServiceGroupDao customservicegroupdao;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return customservicegroupdao.find(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return customservicegroupdao.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return customservicegroupdao.update(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return customservicegroupdao.delete(Object);
}


}