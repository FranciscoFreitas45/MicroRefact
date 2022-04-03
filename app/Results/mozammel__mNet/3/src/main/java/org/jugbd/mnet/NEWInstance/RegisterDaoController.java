package org.jugbd.mnet.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RegisterDaoController {

 private RegisterDao registerdao;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return registerdao.findOne(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return registerdao.save(Object);
}


}