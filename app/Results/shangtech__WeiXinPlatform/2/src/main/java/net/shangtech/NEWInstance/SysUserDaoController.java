package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysUserDaoController {

 private SysUserDao sysuserdao;


@GetMapping
("/findUnique")
public Object findUnique(@RequestParam(name = "Object") Object Object){
  return sysuserdao.findUnique(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return sysuserdao.find(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return sysuserdao.update(Object);
}


}