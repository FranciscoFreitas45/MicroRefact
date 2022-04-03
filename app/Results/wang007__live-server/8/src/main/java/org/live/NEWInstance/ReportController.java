package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReportController {

 private ReportRepository reportrepository;


@PutMapping
("/setUser/{id}")
public void setUser(@PathVariable(name = "id") String id,@RequestParam(name = "user") MobileUser user){
 reportrepository.setUser(id,user);
}


@PutMapping
("/setLiveRoom/{id}")
public void setLiveRoom(@PathVariable(name = "id") String id,@RequestParam(name = "liveRoom") LiveRoom liveRoom){
 reportrepository.setLiveRoom(id,liveRoom);
}


@PutMapping
("/setReportNum/{id}")
public void setReportNum(@PathVariable(name = "id") String id,@RequestParam(name = "reportNum") String reportNum){
 reportrepository.setReportNum(id,reportNum);
}


@PutMapping
("/setCreateTime/{id}")
public void setCreateTime(@PathVariable(name = "id") String id,@RequestParam(name = "createTime") Date createTime){
 reportrepository.setCreateTime(id,createTime);
}


@PutMapping
("/setHandleType/{id}")
public void setHandleType(@PathVariable(name = "id") String id,@RequestParam(name = "handleType") boolean handleType){
 reportrepository.setHandleType(id,handleType);
}


}