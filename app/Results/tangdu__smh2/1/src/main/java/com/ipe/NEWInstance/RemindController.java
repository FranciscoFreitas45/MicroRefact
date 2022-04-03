package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RemindController {

 private Remind remind;

 private Remind remind;


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") String userId){
remind.setUserId(userId);
}


@PutMapping
("/setCreatedDate")
public void setCreatedDate(@RequestParam(name = "createdDate") Date createdDate){
remind.setCreatedDate(createdDate);
}


}