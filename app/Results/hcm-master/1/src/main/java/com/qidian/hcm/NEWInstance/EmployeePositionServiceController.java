package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeePositionServiceController {

 private EmployeePositionService employeepositionservice;


@GetMapping
("/getEmployeeCurrentPositionMap")
public Map<Long,EmployeePosition> getEmployeeCurrentPositionMap(@RequestParam(name = "employeeIds") List<Long> employeeIds){
  return employeepositionservice.getEmployeeCurrentPositionMap(employeeIds);
}


@GetMapping
("/findAllByCondition")
public List<EmployeePosition> findAllByCondition(@RequestParam(name = "condition") Specification<EmployeePosition> condition){
  return employeepositionservice.findAllByCondition(condition);
}


@GetMapping
("/findAllByExcludeEmployeeStatus")
public List<EmployeePosition> findAllByExcludeEmployeeStatus(@RequestParam(name = "status") EmployeeStatus status){
  return employeepositionservice.findAllByExcludeEmployeeStatus(status);
}


@GetMapping
("/getEmployeePositionDTOList")
public List<EmployeePositionDTO> getEmployeePositionDTOList(@RequestParam(name = "employeeId") Long employeeId){
  return employeepositionservice.getEmployeePositionDTOList(employeeId);
}


@PutMapping
("/saveEmployeePositions")
public void saveEmployeePositions(@RequestParam(name = "employeeId") Long employeeId,@RequestParam(name = "employeePositionDTOList") List<EmployeePositionDTO> employeePositionDTOList){
employeepositionservice.saveEmployeePositions(employeeId,employeePositionDTOList);
}


@PutMapping
("/addEmployeePositionForTransferredEmployee")
public void addEmployeePositionForTransferredEmployee(@RequestParam(name = "employeeId") Long employeeId,@RequestParam(name = "transferEmployeeRequest") TransferEmployeeRequest transferEmployeeRequest){
employeepositionservice.addEmployeePositionForTransferredEmployee(employeeId,transferEmployeeRequest);
}


@PutMapping
("/updateLeaderId")
public void updateLeaderId(@RequestParam(name = "employeeId") Long employeeId,@RequestParam(name = "handoverManId") Long handoverManId){
employeepositionservice.updateLeaderId(employeeId,handoverManId);
}


@GetMapping
("/getCurrentEmployeePosition")
public EmployeePosition getCurrentEmployeePosition(@RequestParam(name = "employeeId") Long employeeId){
  return employeepositionservice.getCurrentEmployeePosition(employeeId);
}


}