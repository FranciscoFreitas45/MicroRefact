package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CustomServiceDaoController {

 private CustomServiceDao customservicedao;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return customservicedao.find(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return customservicedao.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return customservicedao.update(Object);
}


}