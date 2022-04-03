package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICircleServiceController {

 private ICircleService icircleservice;


@GetMapping
("/queryCircleByCircleId")
public Circle queryCircleByCircleId(@RequestParam(name = "circleId") String circleId){
  return icircleservice.queryCircleByCircleId(circleId);
}


@GetMapping
("/queryCircleMemberAuditByCircleIdAndUserId")
public int queryCircleMemberAuditByCircleIdAndUserId(@RequestParam(name = "circleId") String circleId,@RequestParam(name = "userId") String userId){
  return icircleservice.queryCircleMemberAuditByCircleIdAndUserId(circleId,userId);
}


@GetMapping
("/AddUserToCircleAudit")
public boolean AddUserToCircleAudit(@RequestParam(name = "userId") String userId,@RequestParam(name = "circleId") String circleId,@RequestParam(name = "type") String type,@RequestParam(name = "status") String status){
  return icircleservice.AddUserToCircleAudit(userId,circleId,type,status);
}


@GetMapping
("/queryCircleMemberByCircleIdAndUserId")
public int queryCircleMemberByCircleIdAndUserId(@RequestParam(name = "circleId") String circleId,@RequestParam(name = "userId") String userId){
  return icircleservice.queryCircleMemberByCircleIdAndUserId(circleId,userId);
}


@GetMapping
("/AddUserToCircle")
public boolean AddUserToCircle(@RequestParam(name = "userId") int userId,@RequestParam(name = "circleId") String circleId,@RequestParam(name = "type") String type){
  return icircleservice.AddUserToCircle(userId,circleId,type);
}


@GetMapping
("/queryMyCircleList")
public List<Circle> queryMyCircleList(@RequestParam(name = "userId") int userId){
  return icircleservice.queryMyCircleList(userId);
}


@GetMapping
("/queryCirclesByUserId")
public List<Circle> queryCirclesByUserId(@RequestParam(name = "userId") Object userId){
  return icircleservice.queryCirclesByUserId(userId);
}


@GetMapping
("/addCicle")
public int addCicle(@RequestParam(name = "circle") Circle circle){
  return icircleservice.addCicle(circle);
}


@GetMapping
("/memberExitCircle")
public Integer memberExitCircle(@RequestParam(name = "cirlce") Circle cirlce,@RequestParam(name = "user") User user){
  return icircleservice.memberExitCircle(cirlce,user);
}


@GetMapping
("/queryCircleAndUserByCircleId")
public Circle queryCircleAndUserByCircleId(@RequestParam(name = "paramMap") Map<String,Object> paramMap){
  return icircleservice.queryCircleAndUserByCircleId(paramMap);
}


@GetMapping
("/queryUsersByCircleId")
public List<User> queryUsersByCircleId(@RequestParam(name = "paramMap") Map<String,Object> paramMap){
  return icircleservice.queryUsersByCircleId(paramMap);
}


@GetMapping
("/saveCircleInfo")
public Integer saveCircleInfo(@RequestParam(name = "paramMap") Map<String,Object> paramMap){
  return icircleservice.saveCircleInfo(paramMap);
}


@GetMapping
("/saveCirclePublishInfo")
public Integer saveCirclePublishInfo(@RequestParam(name = "circle") Circle circle,@RequestParam(name = "_circle") Circle _circle,@RequestParam(name = "user") User user){
  return icircleservice.saveCirclePublishInfo(circle,_circle,user);
}


@GetMapping
("/queryCircleListCircleId")
public List<Circle> queryCircleListCircleId(@RequestParam(name = "circlrIds") String circlrIds){
  return icircleservice.queryCircleListCircleId(circlrIds);
}


}