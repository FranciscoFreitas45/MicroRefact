package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NotificationComponentController {

 private NotificationComponent notificationcomponent;


@PutMapping
("/sendNotification")
public void sendNotification(@RequestParam(name = "userIdx") Integer userIdx,@RequestParam(name = "message") String message){
notificationcomponent.sendNotification(userIdx,message);
}


}