package com.cg.sprint.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ShowsDaoController {

 private ShowsDao showsdao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return showsdao.save(Object);
}


@GetMapping
("/getShowList")
public List<Shows> getShowList(){
  return showsdao.getShowList();
}


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return showsdao.existsById(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return showsdao.deleteById(Object);
}


@GetMapping
("/getShows")
public List<Shows> getShows(){
  return showsdao.getShows();
}


}