package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CheckinServiceController {

 private CheckinService checkinservice;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return checkinservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return checkinservice.queryByPager(Object);
}


@GetMapping
("/countByDisable")
public Object countByDisable(@RequestParam(name = "Object") Object Object){
  return checkinservice.countByDisable(Object);
}


@GetMapping
("/queryByPagerDisable")
public Object queryByPagerDisable(@RequestParam(name = "Object") Object Object){
  return checkinservice.queryByPagerDisable(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return checkinservice.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return checkinservice.update(Object);
}


@GetMapping
("/inactive")
public Object inactive(@RequestParam(name = "Object") Object Object){
  return checkinservice.inactive(Object);
}


@GetMapping
("/active")
public Object active(@RequestParam(name = "Object") Object Object){
  return checkinservice.active(Object);
}


@GetMapping
("/blurredQuery")
public Object blurredQuery(@RequestParam(name = "Object") Object Object){
  return checkinservice.blurredQuery(Object);
}


@GetMapping
("/countByBlurred")
public Object countByBlurred(@RequestParam(name = "Object") Object Object){
  return checkinservice.countByBlurred(Object);
}


@GetMapping
("/queryByPhone")
public List<Checkin> queryByPhone(@RequestParam(name = "userPhone") String userPhone){
  return checkinservice.queryByPhone(userPhone);
}


@PutMapping
("/updateUserIds")
public void updateUserIds(@RequestParam(name = "userId") String userId,@RequestParam(name = "chIds") String chIds){
checkinservice.updateUserIds(userId,chIds);
}


}