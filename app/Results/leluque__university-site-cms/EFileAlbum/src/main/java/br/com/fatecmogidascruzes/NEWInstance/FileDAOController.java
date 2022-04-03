package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileDAOController {

 private FileDAO filedao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return filedao.save(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return filedao.findById(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return filedao.deleteById(Object);
}


@GetMapping
("/findByHash")
public Object findByHash(@RequestParam(name = "Object") Object Object){
  return filedao.findByHash(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return filedao.delete(Object);
}


}