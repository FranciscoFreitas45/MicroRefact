package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.room.entity.Room;
@RestController
@CrossOrigin
public class RoomFloorController {

@Autowired
 private RoomFloorService roomfloorservice;


@PutMapping
("/Floor/{id}/Room/setChildNodes")
public void setChildNodes(@PathVariable(name="id") Long floorId,@RequestBody List<Room> childNodes){
roomfloorservice.setChildNodes(floorId,childNodes);
}


@GetMapping
("/Floor/{id}/Room/getChildNodes")
public List<Room> getChildNodes(@PathVariable(name="id") Long floorId){
return roomfloorservice.getChildNodes(floorId);
}


}