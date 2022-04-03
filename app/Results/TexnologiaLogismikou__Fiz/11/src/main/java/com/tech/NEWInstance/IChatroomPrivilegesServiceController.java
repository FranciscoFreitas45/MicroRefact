package com.tech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IChatroomPrivilegesServiceController {

 private IChatroomPrivilegesService ichatroomprivilegesservice;


@PutMapping
("/add")
public void add(@RequestParam(name = "newRecord") ChatroomPrivileges newRecord){
ichatroomprivilegesservice.add(newRecord);
}


@GetMapping
("/findByRoomId")
public ChatroomPrivileges findByRoomId(@RequestParam(name = "room_id") Long room_id){
  return ichatroomprivilegesservice.findByRoomId(room_id);
}


@PutMapping
("/setChatroomPrivileges")
public void setChatroomPrivileges(@RequestParam(name = "room_privileges") String room_privileges,@RequestParam(name = "room_password_protected") Boolean room_password_protected,@RequestParam(name = "room_password") String room_password,@RequestParam(name = "room_access_method") String room_access_method,@RequestParam(name = "room_id") Long room_id){
ichatroomprivilegesservice.setChatroomPrivileges(room_privileges,room_password_protected,room_password,room_access_method,room_id);
}


}