package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.employee.entity.Employee;
@RestController
@CrossOrigin
public class EmployeeRoomCleanRecordController {

@Autowired
 private EmployeeRoomCleanRecordService employeeroomcleanrecordservice;


@PutMapping
("/RoomCleanRecord/{id}/Employee/setRoomWorker")
public void setRoomWorker(@PathVariable(name="id") Integer emp_idU6JN,@RequestBody Employee roomWorker){
employeeroomcleanrecordservice.setRoomWorker(emp_idU6JN,roomWorker);
}


@GetMapping
("/RoomCleanRecord/{id}/Employee/getRoomWorker")
public Employee getRoomWorker(@PathVariable(name="id") Integer emp_idU6JN){
return employeeroomcleanrecordservice.getRoomWorker(emp_idU6JN);
}


}