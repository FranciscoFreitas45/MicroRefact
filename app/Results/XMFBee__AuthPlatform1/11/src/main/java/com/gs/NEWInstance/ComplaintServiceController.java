package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ComplaintServiceController {

 private ComplaintService complaintservice;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return complaintservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return complaintservice.queryByPager(Object);
}


@GetMapping
("/countComplaintUser")
public int countComplaintUser(@RequestParam(name = "userId") String userId){
  return complaintservice.countComplaintUser(userId);
}


@GetMapping
("/queryByPagerComplaintUser")
public List<Complaint> queryByPagerComplaintUser(@RequestParam(name = "pager") Pager pager,@RequestParam(name = "userId") String userId){
  return complaintservice.queryByPagerComplaintUser(pager,userId);
}


@GetMapping
("/countName")
public int countName(@RequestParam(name = "complaint") Complaint complaint,@RequestParam(name = "user") User user){
  return complaintservice.countName(complaint,user);
}


@GetMapping
("/queryByPagerName")
public List<Complaint> queryByPagerName(@RequestParam(name = "pager") Pager pager,@RequestParam(name = "complaint") Complaint complaint){
  return complaintservice.queryByPagerName(pager,complaint);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return complaintservice.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return complaintservice.update(Object);
}


}