package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmailHelperController {

 private EmailHelper emailhelper;


@PutMapping
("/sendAccountActivationEmail")
public void sendAccountActivationEmail(@RequestParam(name = "user") User user,@RequestParam(name = "userToken") UserToken userToken){
emailhelper.sendAccountActivationEmail(user,userToken);
}


}