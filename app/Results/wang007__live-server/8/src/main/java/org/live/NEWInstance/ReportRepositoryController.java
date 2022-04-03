package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReportRepositoryController {

 private ReportRepository reportrepository;


@GetMapping
("/getRecentlyReport")
public Report getRecentlyReport(@RequestParam(name = "userId") String userId,@RequestParam(name = "liveRoomId") String liveRoomId){
  return reportrepository.getRecentlyReport(userId,liveRoomId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return reportrepository.save(Object);
}


}