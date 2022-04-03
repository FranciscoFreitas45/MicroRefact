package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LiveRoomServiceController {

 private LiveRoomService liveroomservice;


@GetMapping
("/findLiveRoomByMobileUser")
public LiveRoom findLiveRoomByMobileUser(@RequestParam(name = "user") MobileUser user){
  return liveroomservice.findLiveRoomByMobileUser(user);
}


@GetMapping
("/getLiveRoomByRoomNum")
public LiveRoom getLiveRoomByRoomNum(@RequestParam(name = "roomNum") String roomNum){
  return liveroomservice.getLiveRoomByRoomNum(roomNum);
}


@PutMapping
("/reportLiveRoom")
public void reportLiveRoom(@RequestParam(name = "userId") String userId,@RequestParam(name = "liveRoomId") String liveRoomId){
liveroomservice.reportLiveRoom(userId,liveRoomId);
}


}