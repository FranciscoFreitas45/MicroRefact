package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MessageController {

 private Message message;

 private Message message;


@PutMapping
("/setCriticality")
public void setCriticality(@RequestParam(name = "criticality") int criticality){
message.setCriticality(criticality);
}


@PutMapping
("/setText")
public void setText(@RequestParam(name = "text") String text){
message.setText(text);
}


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") Integer userId){
message.setUserId(userId);
}


}