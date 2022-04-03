package com.tech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IChatroomMembersServiceController {

 private IChatroomMembersService ichatroommembersservice;


@PutMapping
("/add")
public void add(@RequestParam(name = "newRecord") ChatroomMembers newRecord){
ichatroommembersservice.add(newRecord);
}


@GetMapping
("/checkIfMemberExistsInChatroom")
public boolean checkIfMemberExistsInChatroom(@RequestParam(name = "member_id") Long member_id,@RequestParam(name = "room_id") Long room_id){
  return ichatroommembersservice.checkIfMemberExistsInChatroom(member_id,room_id);
}


@GetMapping
("/delete")
public boolean delete(@RequestParam(name = "deleteRecord") ChatroomMembers deleteRecord){
  return ichatroommembersservice.delete(deleteRecord);
}


}