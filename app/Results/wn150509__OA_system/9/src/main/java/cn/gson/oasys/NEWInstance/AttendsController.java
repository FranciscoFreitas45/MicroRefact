package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttendsController {

 private AttendceDao attendcedao;


@PutMapping
("/setHolidayStart/{id}")
public void setHolidayStart(@PathVariable(name = "id") Long id,@RequestParam(name = "holidayStart") Date holidayStart){
 attendcedao.setHolidayStart(id,holidayStart);
}


@PutMapping
("/setUser/{id}")
public void setUser(@PathVariable(name = "id") Long id,@RequestParam(name = "user") User user){
 attendcedao.setUser(id,user);
}


@PutMapping
("/setStatusId/{id}")
public void setStatusId(@PathVariable(name = "id") Long id,@RequestParam(name = "statusId") Long statusId){
 attendcedao.setStatusId(id,statusId);
}


}