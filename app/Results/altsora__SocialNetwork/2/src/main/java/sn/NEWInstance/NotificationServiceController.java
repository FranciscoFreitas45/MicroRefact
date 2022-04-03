package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NotificationServiceController {

 private NotificationService notificationservice;


@GetMapping
("/saveNotificationSettings")
public ServiceResponse<ResponseDataMessage> saveNotificationSettings(@RequestParam(name = "person") Person person,@RequestParam(name = "request") NotificationSettingRequest request){
  return notificationservice.saveNotificationSettings(person,request);
}


}