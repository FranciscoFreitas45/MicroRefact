package com.puffride.demo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RideDaoController {

 private RideDao ridedao;


@GetMapping
("/findAll")
public List<Ride> findAll(){
  return ridedao.findAll();
}


@GetMapping
("/stream")
public Object stream(@RequestParam(name = "Object") Object Object){
  return ridedao.stream(Object);
}


@GetMapping
("/filter")
public Object filter(@RequestParam(name = "Object") Object Object){
  return ridedao.filter(Object);
}


}