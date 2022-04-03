package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NotifiationServiceController {

 private NotifiationService notifiationservice;


@GetMapping
("/getNewNotiCount")
public int getNewNotiCount(@RequestParam(name = "userIdx") int userIdx){
  return notifiationservice.getNewNotiCount(userIdx);
}


}