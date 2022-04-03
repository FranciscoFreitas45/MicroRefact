package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;


@GetMapping
("/getId")
public int getId(){
  return user.getId();
}


@GetMapping
("/getName")
public String getName(){
  return user.getName();
}


@PutMapping
("/setNickname")
public void setNickname(@RequestParam(name = "nickname") String nickname){
user.setNickname(nickname);
}


@PutMapping
("/setWeixinName")
public void setWeixinName(@RequestParam(name = "weixinName") String weixinName){
user.setWeixinName(weixinName);
}


@PutMapping
("/setWeixinImage")
public void setWeixinImage(@RequestParam(name = "weixinImage") String weixinImage){
user.setWeixinImage(weixinImage);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
user.setName(name);
}


@PutMapping
("/setInviteUserId")
public void setInviteUserId(@RequestParam(name = "inviteUserId") Integer inviteUserId){
user.setInviteUserId(inviteUserId);
}


@PutMapping
("/setInviteCode")
public void setInviteCode(@RequestParam(name = "inviteCode") String inviteCode){
user.setInviteCode(inviteCode);
}


@PutMapping
("/setImagePath")
public void setImagePath(@RequestParam(name = "imagePath") String imagePath){
user.setImagePath(imagePath);
}


}