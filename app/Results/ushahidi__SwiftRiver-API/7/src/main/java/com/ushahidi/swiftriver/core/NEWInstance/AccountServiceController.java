package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccountServiceController {

 private AccountService accountservice;


@PutMapping
("/logActivity")
public void logActivity(@RequestParam(name = "account") Account account,@RequestParam(name = "action") ActivityType action,@RequestParam(name = "actionOn") Object actionOn){
accountservice.logActivity(account,action,actionOn);
}


}