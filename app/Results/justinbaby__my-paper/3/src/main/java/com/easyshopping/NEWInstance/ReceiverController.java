package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReceiverController {

 private Receiver receiver;

 private Receiver receiver;


@PutMapping
("/setArea")
public void setArea(@RequestParam(name = "area") Area area){
receiver.setArea(area);
}


@PutMapping
("/setMember")
public void setMember(@RequestParam(name = "member") Member member){
receiver.setMember(member);
}


}