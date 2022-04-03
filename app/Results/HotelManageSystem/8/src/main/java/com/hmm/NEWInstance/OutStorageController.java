package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OutStorageController {

 private OutStorage outstorage;

 private OutStorage outstorage;


@PutMapping
("/setRoomCleanRecord")
public void setRoomCleanRecord(@RequestParam(name = "roomCleanRecord") RoomCleanRecord roomCleanRecord){
outstorage.setRoomCleanRecord(roomCleanRecord);
}


@PutMapping
("/setRoomNo")
public void setRoomNo(@RequestParam(name = "roomNo") String roomNo){
outstorage.setRoomNo(roomNo);
}


}