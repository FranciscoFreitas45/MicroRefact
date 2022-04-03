package com.tech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IChatroomBlacklistServiceController {

 private IChatroomBlacklistService ichatroomblacklistservice;


@GetMapping
("/findByRoomIDAndRoomMember")
public ChatroomBlacklist findByRoomIDAndRoomMember(@RequestParam(name = "room_id") Long room_id,@RequestParam(name = "room_member") Long room_member){
  return ichatroomblacklistservice.findByRoomIDAndRoomMember(room_id,room_member);
}


@PutMapping
("/setNewTime")
public void setNewTime(@RequestParam(name = "room_id") Long room_id,@RequestParam(name = "member_id") Long member_id,@RequestParam(name = "room_expiration_time") Date room_expiration_time){
ichatroomblacklistservice.setNewTime(room_id,member_id,room_expiration_time);
}


@GetMapping
("/delete")
public boolean delete(@RequestParam(name = "deleteRecord") ChatroomBlacklist deleteRecord){
  return ichatroomblacklistservice.delete(deleteRecord);
}


@PutMapping
("/add")
public void add(@RequestParam(name = "newRecord") ChatroomBlacklist newRecord){
ichatroomblacklistservice.add(newRecord);
}


}