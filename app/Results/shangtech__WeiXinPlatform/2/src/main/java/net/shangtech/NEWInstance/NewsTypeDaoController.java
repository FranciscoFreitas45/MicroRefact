package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NewsTypeDaoController {

 private NewsTypeDao newstypedao;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return newstypedao.find(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return newstypedao.delete(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return newstypedao.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return newstypedao.update(Object);
}


}