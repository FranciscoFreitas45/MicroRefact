package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ApplyAnchorServiceController {

 private ApplyAnchorService applyanchorservice;


@GetMapping
("/judgeUserApplyCount")
public boolean judgeUserApplyCount(@RequestParam(name = "userId") String userId,@RequestParam(name = "systemMaxCount") int systemMaxCount){
  return applyanchorservice.judgeUserApplyCount(userId,systemMaxCount);
}


@GetMapping
("/getLastApplyAnchorDate")
public Date getLastApplyAnchorDate(@RequestParam(name = "userId") String userId){
  return applyanchorservice.getLastApplyAnchorDate(userId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return applyanchorservice.save(Object);
}


}