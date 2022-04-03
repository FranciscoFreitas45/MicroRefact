package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.logistics.roomClean.entity.RoomCleanRecord;
@RestController
@CrossOrigin
public class RoomCleanRecordOutStorageController {

@Autowired
 private RoomCleanRecordOutStorageService roomcleanrecordoutstorageservice;


@PutMapping
("/OutStorage/{id}/RoomCleanRecord/setRoomCleanRecord")
public void setRoomCleanRecord(@PathVariable(name="id") Long idOU5W,@RequestBody RoomCleanRecord roomCleanRecord){
roomcleanrecordoutstorageservice.setRoomCleanRecord(idOU5W,roomCleanRecord);
}


@GetMapping
("/OutStorage/{id}/RoomCleanRecord/getRoomCleanRecord")
public RoomCleanRecord getRoomCleanRecord(@PathVariable(name="id") Long idOU5W){
return roomcleanrecordoutstorageservice.getRoomCleanRecord(idOU5W);
}


}