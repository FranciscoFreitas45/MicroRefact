package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LzWeiMemberController {

 private LzWeiMember lzweimember;

 private LzWeiMember lzweimember;


@PutMapping
("/setWmbWecId_Q")
public void setWmbWecId_Q(@RequestParam(name = "wmbWecId_Q") Integer wmbWecId_Q){
lzweimember.setWmbWecId_Q(wmbWecId_Q);
}


}