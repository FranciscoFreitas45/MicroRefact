package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnchorServiceController {

 private AnchorService anchorservice;


@GetMapping
("/findAnchorForAppUser")
public AppAnchorInfo findAnchorForAppUser(@RequestParam(name = "userId") String userId,@RequestParam(name = "liveRoomId") String liveRoomId){
  return anchorservice.findAnchorForAppUser(userId,liveRoomId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return anchorservice.save(Object);
}


}