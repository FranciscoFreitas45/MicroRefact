package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NewsDaoController {

 private NewsDao newsdao;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return newsdao.find(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return newsdao.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return newsdao.update(Object);
}


}