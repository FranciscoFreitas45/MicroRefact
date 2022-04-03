package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DepartmentDaoController {

 private DepartmentDao departmentdao;


@GetMapping
("/findUniqueByProperty")
public Object findUniqueByProperty(@RequestParam(name = "Object") Object Object){
  return departmentdao.findUniqueByProperty(Object);
}


@GetMapping
("/findAllDepartment")
public List<DepartmentSelectDto> findAllDepartment(){
  return departmentdao.findAllDepartment();
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return departmentdao.findById(Object);
}


}