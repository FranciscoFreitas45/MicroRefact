package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeesTrainingLogDaoController {

 private EmployeesTrainingLogDao employeestraininglogdao;


@GetMapping
("/findTrainingRecord")
public List<EmployeesTrainingRecordDto> findTrainingRecord(@RequestParam(name = "emplId") long emplId){
  return employeestraininglogdao.findTrainingRecord(emplId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return employeestraininglogdao.save(Object);
}


@GetMapping
("/findByEmplTraining")
public TeEmployeesTrainingLog findByEmplTraining(@RequestParam(name = "emplId") long emplId,@RequestParam(name = "trainingId") long trainingId){
  return employeestraininglogdao.findByEmplTraining(emplId,trainingId);
}


@GetMapping
("/deleteByProperty")
public Object deleteByProperty(@RequestParam(name = "Object") Object Object){
  return employeestraininglogdao.deleteByProperty(Object);
}


}