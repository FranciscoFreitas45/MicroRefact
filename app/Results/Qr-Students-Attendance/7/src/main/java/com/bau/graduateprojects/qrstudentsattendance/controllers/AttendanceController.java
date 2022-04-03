package com.bau.graduateprojects.qrstudentsattendance.controllers;
 import com.bau.graduateprojects.qrstudentsattendance.entities.AttendanceEntity;
import com.bau.graduateprojects.qrstudentsattendance.servicies.attendance.AttendanceService;
import org.springframework.web.bind.annotation;
@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

 private  AttendanceService attendanceService;

public AttendanceController(AttendanceService attendanceService) {
    this.attendanceService = attendanceService;
}
@GetMapping("/id/{id}")
public AttendanceEntity getAttendanceById(Long id){
    return attendanceService.getAttendanceById(id);
}


@PostMapping
public AttendanceEntity insert(AttendanceEntity entity){
    return attendanceService.insert(entity);
}


}