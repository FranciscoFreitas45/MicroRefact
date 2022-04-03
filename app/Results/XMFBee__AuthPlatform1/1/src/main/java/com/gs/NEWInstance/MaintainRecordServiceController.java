package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MaintainRecordServiceController {

 private MaintainRecordService maintainrecordservice;


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return maintainrecordservice.insert(Object);
}


@PutMapping
("/updateCurrentStatus")
public void updateCurrentStatus(@RequestParam(name = "currentStatus") String currentStatus,@RequestParam(name = "recordId") String recordId){
maintainrecordservice.updateCurrentStatus(currentStatus,recordId);
}


@PutMapping
("/updatePickupTime")
public void updatePickupTime(@RequestParam(name = "maintainRecordId") String maintainRecordId){
maintainrecordservice.updatePickupTime(maintainRecordId);
}


@PutMapping
("/updateActualEndTime")
public void updateActualEndTime(@RequestParam(name = "maintainRecordId") String maintainRecordId){
maintainrecordservice.updateActualEndTime(maintainRecordId);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return maintainrecordservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return maintainrecordservice.queryByPager(Object);
}


@GetMapping
("/blurredQueryByRemind")
public List<MaintainRecord> blurredQueryByRemind(@RequestParam(name = "pager") Pager pager,@RequestParam(name = "maintainRecord") MaintainRecord maintainRecord){
  return maintainrecordservice.blurredQueryByRemind(pager,maintainRecord);
}


@GetMapping
("/countByBlurredByRemind")
public int countByBlurredByRemind(@RequestParam(name = "maintainRecord") MaintainRecord maintainRecord,@RequestParam(name = "user") User user){
  return maintainrecordservice.countByBlurredByRemind(maintainRecord,user);
}


@GetMapping
("/countSix")
public int countSix(@RequestParam(name = "user") User user,@RequestParam(name = "actualEndTime") String actualEndTime){
  return maintainrecordservice.countSix(user,actualEndTime);
}


@GetMapping
("/queryByPagerSix")
public List<MaintainRecord> queryByPagerSix(@RequestParam(name = "pager") Pager pager,@RequestParam(name = "actualEndTime") String actualEndTime){
  return maintainrecordservice.queryByPagerSix(pager,actualEndTime);
}


}