package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SendSmsController {

 private SendSms sendsms;

 private SendSms sendsms;


@GetMapping
("/sendSms")
public boolean sendSms(@RequestParam(name = "phoneNum") String phoneNum,@RequestParam(name = "realName") String realName,@RequestParam(name = "clubName") String clubName,@RequestParam(name = "thing") String thing,@RequestParam(name = "state") String state){
  return sendsms.sendSms(phoneNum,realName,clubName,thing,state);
}


}