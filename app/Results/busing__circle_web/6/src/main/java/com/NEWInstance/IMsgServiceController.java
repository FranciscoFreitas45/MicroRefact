package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IMsgServiceController {

 private IMsgService imsgservice;


@GetMapping
("/queryCircleMsgCount")
public int queryCircleMsgCount(@RequestParam(name = "sendUserId") String sendUserId,@RequestParam(name = "reciveUserId") String reciveUserId){
  return imsgservice.queryCircleMsgCount(sendUserId,reciveUserId);
}


@GetMapping
("/getUserMessage")
public List<Map<String,Object>> getUserMessage(@RequestParam(name = "page") Page page,@RequestParam(name = "id") int id){
  return imsgservice.getUserMessage(page,id);
}


}