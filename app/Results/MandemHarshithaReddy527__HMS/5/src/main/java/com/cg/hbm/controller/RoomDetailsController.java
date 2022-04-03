package com.cg.hbm.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.hbm.entites.RoomDetails;
import com.cg.hbm.exceptions.RoomDetailsNotFoundException;
import com.cg.hbm.service.IRoomDetailsService;
@RestController
@RequestMapping("/room_details")
public class RoomDetailsController {

@Autowired
 private IRoomDetailsService rService;


@PutMapping("/roomDetails")
public ResponseEntity<RoomDetails> updateRoomDetails(RoomDetails roomDetails){
    RoomDetails resultRoomDetails = rService.updateRoomDetails(roomDetails.getRoom_id(), roomDetails);
    return new ResponseEntity<RoomDetails>(resultRoomDetails, HttpStatus.OK);
}


@PostMapping("/add")
public ResponseEntity<RoomDetails> addHotel(RoomDetails roomDetails){
    RoomDetails resultRoomDetails = rService.addRoomDetails(roomDetails);
    return new ResponseEntity<RoomDetails>(resultRoomDetails, HttpStatus.OK);
}


@DeleteMapping("/{room_id}")
public RoomDetails removeRoomDetails(int room_id){
    return rService.removeRoomDetails(room_id);
}


@GetMapping("/all")
public ResponseEntity<List<RoomDetails>> showAllRoomDetails(){
    List<RoomDetails> resultRoomDetails = rService.showAllRoomDetails();
    return new ResponseEntity<List<RoomDetails>>(resultRoomDetails, HttpStatus.OK);
}


@GetMapping("/{room_id}")
public ResponseEntity<RoomDetails> showRoomDetails(int room_id){
    RoomDetails r = rService.showRoomDetails(room_id);
    if (r != null) {
        return new ResponseEntity<RoomDetails>(r, HttpStatus.OK);
    } else {
        return new ResponseEntity<RoomDetails>(HttpStatus.NOT_FOUND);
    }
}


}