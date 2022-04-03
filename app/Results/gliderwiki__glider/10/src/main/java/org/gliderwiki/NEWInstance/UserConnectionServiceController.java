package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserConnectionServiceController {

 private UserConnectionService userconnectionservice;


@GetMapping
("/getMyConnection")
public List<AddFriendVo> getMyConnection(@RequestParam(name = "memberSessionVo") MemberSessionVo memberSessionVo){
  return userconnectionservice.getMyConnection(memberSessionVo);
}


@GetMapping
("/getConnectionToMe")
public List<AddFriendVo> getConnectionToMe(@RequestParam(name = "weUserIdx") Integer weUserIdx){
  return userconnectionservice.getConnectionToMe(weUserIdx);
}


@GetMapping
("/addWeFriends")
public int addWeFriends(@RequestParam(name = "arrayCheckId") String arrayCheckId,@RequestParam(name = "weUserIdx") Integer weUserIdx){
  return userconnectionservice.addWeFriends(arrayCheckId,weUserIdx);
}


}