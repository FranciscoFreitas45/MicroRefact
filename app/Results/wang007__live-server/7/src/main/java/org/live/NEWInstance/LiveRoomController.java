package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LiveRoomController {

 private LiveRoomRepository liveroomrepository;


@PutMapping
("/setLiveCategory/{id}")
public void setLiveCategory(@PathVariable(name = "id") String id,@RequestParam(name = "liveCategory") LiveCategory liveCategory){
 liveroomrepository.setLiveCategory(id,liveCategory);
}


@PutMapping
("/setRoomNum/{id}")
public void setRoomNum(@PathVariable(name = "id") String id,@RequestParam(name = "roomNum") String roomNum){
 liveroomrepository.setRoomNum(id,roomNum);
}


@PutMapping
("/setRoomLabel/{id}")
public void setRoomLabel(@PathVariable(name = "id") String id,@RequestParam(name = "roomLabel") String roomLabel){
 liveroomrepository.setRoomLabel(id,roomLabel);
}


@PutMapping
("/setLiveRoomUrl/{id}")
public void setLiveRoomUrl(@PathVariable(name = "id") String id,@RequestParam(name = "liveRoomUrl") String liveRoomUrl){
 liveroomrepository.setLiveRoomUrl(id,liveRoomUrl);
}


@PutMapping
("/setCoverUrl/{id}")
public void setCoverUrl(@PathVariable(name = "id") String id,@RequestParam(name = "coverUrl") String coverUrl){
 liveroomrepository.setCoverUrl(id,coverUrl);
}


@PutMapping
("/setRoomName/{id}")
public void setRoomName(@PathVariable(name = "id") String id,@RequestParam(name = "roomName") String roomName){
 liveroomrepository.setRoomName(id,roomName);
}


@GetMapping
("/isBanLiveFlag/{id}")
public boolean isBanLiveFlag(@PathVariable(name = "id") String id){
 return liveroomrepository.isBanLiveFlag(id);
}


}