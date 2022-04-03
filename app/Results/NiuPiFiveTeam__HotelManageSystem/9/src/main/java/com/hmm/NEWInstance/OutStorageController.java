package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OutStorageController {

 private OutStorageRepository outstoragerepository;


@PutMapping
("/setRoomCleanRecord/{id}")
public void setRoomCleanRecord(@PathVariable(name = "id") Long id,@RequestParam(name = "roomCleanRecord") RoomCleanRecord roomCleanRecord){
 outstoragerepository.setRoomCleanRecord(id,roomCleanRecord);
}


@PutMapping
("/setRoomNo/{id}")
public void setRoomNo(@PathVariable(name = "id") Long id,@RequestParam(name = "roomNo") String roomNo){
 outstoragerepository.setRoomNo(id,roomNo);
}


}