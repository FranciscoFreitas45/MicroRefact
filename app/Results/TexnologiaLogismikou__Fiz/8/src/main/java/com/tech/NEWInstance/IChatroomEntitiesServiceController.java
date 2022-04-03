package com.tech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IChatroomEntitiesServiceController {

 private IChatroomEntitiesService ichatroomentitiesservice;


@GetMapping
("/validateRoomnameExistance")
public boolean validateRoomnameExistance(@RequestParam(name = "room_name") String room_name){
  return ichatroomentitiesservice.validateRoomnameExistance(room_name);
}


@GetMapping
("/getNextID")
public Long getNextID(){
  return ichatroomentitiesservice.getNextID();
}


@PutMapping
("/add")
public void add(@RequestParam(name = "newRecord") ChatroomEntities newRecord){
ichatroomentitiesservice.add(newRecord);
}


@GetMapping
("/getRoomByName")
public ChatroomEntities getRoomByName(@RequestParam(name = "room_name") String room_name){
  return ichatroomentitiesservice.getRoomByName(room_name);
}


@GetMapping
("/delete")
public boolean delete(@RequestParam(name = "deleteRecord") ChatroomEntities deleteRecord){
  return ichatroomentitiesservice.delete(deleteRecord);
}


@PutMapping
("/setChatroomEntities")
public void setChatroomEntities(@RequestParam(name = "room_name") String room_name,@RequestParam(name = "room_id") Long room_id){
ichatroomentitiesservice.setChatroomEntities(room_name,room_id);
}


@GetMapping
("/findByRoomID")
public ChatroomEntities findByRoomID(@RequestParam(name = "room_id") Long room_id){
  return ichatroomentitiesservice.findByRoomID(room_id);
}


}