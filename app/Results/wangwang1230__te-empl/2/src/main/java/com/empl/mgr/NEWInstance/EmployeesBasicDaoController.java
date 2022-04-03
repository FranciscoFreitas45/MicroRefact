package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeesBasicDaoController {

 private EmployeesBasicDao employeesbasicdao;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return employeesbasicdao.findById(Object);
}


@GetMapping
("/findUniqueByProperty")
public Object findUniqueByProperty(@RequestParam(name = "Object") Object Object){
  return employeesbasicdao.findUniqueByProperty(Object);
}


@PutMapping
("/clearEmplPositionByDeptId")
public void clearEmplPositionByDeptId(@RequestParam(name = "deptId") long deptId){
employeesbasicdao.clearEmplPositionByDeptId(deptId);
}


@PutMapping
("/clearEmplDepartmentByDeptId")
public void clearEmplDepartmentByDeptId(@RequestParam(name = "deptId") long deptId){
employeesbasicdao.clearEmplDepartmentByDeptId(deptId);
}


@GetMapping
("/findByProperty")
public Object findByProperty(@RequestParam(name = "Object") Object Object){
  return employeesbasicdao.findByProperty(Object);
}


@GetMapping
("/findEmplByDeptId")
public List<SelectDto> findEmplByDeptId(@RequestParam(name = "deptId") long deptId){
  return employeesbasicdao.findEmplByDeptId(deptId);
}


@PutMapping
("/chearEmplPositionByPositionId")
public void chearEmplPositionByPositionId(@RequestParam(name = "id") long id){
employeesbasicdao.chearEmplPositionByPositionId(id);
}


}