package com.cg.sprint.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TheatreDaoController {

 private TheatreDao theatredao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return theatredao.save(Object);
}


@GetMapping
("/getTheatreList")
public List<Theatre> getTheatreList(){
  return theatredao.getTheatreList();
}


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return theatredao.existsById(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return theatredao.deleteById(Object);
}


@GetMapping
("/theatreNames")
public List<Theatre> theatreNames(@RequestParam(name = "name") String name){
  return theatredao.theatreNames(name);
}


}