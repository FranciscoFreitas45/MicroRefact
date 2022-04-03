package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DeptDaoController {

 private DeptDao deptdao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return deptdao.findAll(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return deptdao.findOne(Object);
}


@GetMapping
("/findname")
public String findname(@RequestParam(name = "id") Long id){
  return deptdao.findname(id);
}


}