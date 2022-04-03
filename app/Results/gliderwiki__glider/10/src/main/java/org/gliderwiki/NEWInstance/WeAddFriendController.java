package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeAddFriendController {

 private WeAddFriend weaddfriend;

 private WeAddFriend weaddfriend;


@PutMapping
("/setWe_target_user_idx")
public void setWe_target_user_idx(@RequestParam(name = "we_target_user_idx") Integer we_target_user_idx){
weaddfriend.setWe_target_user_idx(we_target_user_idx);
}


@PutMapping
("/setWe_add_date")
public void setWe_add_date(@RequestParam(name = "we_add_date") Date we_add_date){
weaddfriend.setWe_add_date(we_add_date);
}


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
weaddfriend.setWe_use_yn(we_use_yn);
}


}