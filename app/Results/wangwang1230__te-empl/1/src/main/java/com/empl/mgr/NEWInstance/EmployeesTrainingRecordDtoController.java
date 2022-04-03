package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeesTrainingRecordDtoController {

 private EmployeesTrainingRecordDto employeestrainingrecorddto;

 private EmployeesTrainingRecordDto employeestrainingrecorddto;


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") String status){
employeestrainingrecorddto.setStatus(status);
}


}