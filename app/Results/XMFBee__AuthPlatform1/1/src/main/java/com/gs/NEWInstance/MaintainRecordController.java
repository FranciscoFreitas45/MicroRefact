package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MaintainRecordController {

 private MaintainRecord maintainrecord;


@PutMapping
("/setCurrentStatus")
public void setCurrentStatus(@RequestParam(name = "currentStatus") String currentStatus){
maintainrecord.setCurrentStatus(currentStatus);
}


@PutMapping
("/setRecordDes")
public void setRecordDes(@RequestParam(name = "recordDes") String recordDes){
maintainrecord.setRecordDes(recordDes);
}


@PutMapping
("/setIfConfirm")
public void setIfConfirm(@RequestParam(name = "ifConfirm") String ifConfirm){
maintainrecord.setIfConfirm(ifConfirm);
}


@PutMapping
("/setCheckin")
public void setCheckin(@RequestParam(name = "checkin") Checkin checkin){
maintainrecord.setCheckin(checkin);
}


}