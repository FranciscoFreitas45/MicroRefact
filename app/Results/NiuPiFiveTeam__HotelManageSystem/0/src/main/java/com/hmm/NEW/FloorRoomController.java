package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.room.entity.Floor;
@RestController
@CrossOrigin
public class FloorRoomController {

@Autowired
 private FloorRoomService floorroomservice;


@GetMapping
("/Room/{id}/Floor/getFloorNode")
public Floor getFloorNode(@PathVariable(name="id") Long floorIdW7A6){
return floorroomservice.getFloorNode(floorIdW7A6);
}


@PutMapping
("/Room/{id}/Floor/setFloorNode")
public void setFloorNode(@PathVariable(name="id") Long floorIdW7A6,@RequestBody Floor floorNode){
floorroomservice.setFloorNode(floorIdW7A6,floorNode);
}


}