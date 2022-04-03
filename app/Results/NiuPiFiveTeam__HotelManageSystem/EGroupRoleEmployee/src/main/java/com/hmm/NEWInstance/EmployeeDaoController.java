package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeDaoController {

 private EmployeeDao employeedao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return employeedao.save(Object);
}


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return employeedao.existsById(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return employeedao.deleteById(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return employeedao.findAll(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return employeedao.count(Object);
}


@GetMapping
("/findAllById")
public Object findAllById(@RequestParam(name = "Object") Object Object){
  return employeedao.findAllById(Object);
}


@GetMapping
("/deleteAll")
public Object deleteAll(@RequestParam(name = "Object") Object Object){
  return employeedao.deleteAll(Object);
}


@GetMapping
("/findByEmpNo")
public Employee findByEmpNo(@RequestParam(name = "empNo") String empNo){
  return employeedao.findByEmpNo(empNo);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return employeedao.findById(Object);
}


@GetMapping
("/findByEmpNameAndEmpNo")
public Employee findByEmpNameAndEmpNo(@RequestParam(name = "empName") String empName,@RequestParam(name = "empNo") String empNo){
  return employeedao.findByEmpNameAndEmpNo(empName,empNo);
}


@GetMapping
("/findByEmpName")
public Employee findByEmpName(@RequestParam(name = "empName") String empName){
  return employeedao.findByEmpName(empName);
}


@PutMapping
("/updatePassword")
public void updatePassword(@RequestParam(name = "password") String password,@RequestParam(name = "userName") String userName){
employeedao.updatePassword(password,userName);
}


@GetMapping
("/findByUserName")
public Employee findByUserName(@RequestParam(name = "userName") String userName){
  return employeedao.findByUserName(userName);
}


}