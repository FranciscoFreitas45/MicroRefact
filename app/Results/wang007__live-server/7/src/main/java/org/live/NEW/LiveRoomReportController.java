package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.entity.LiveRoom;
@RestController
@CrossOrigin
public class LiveRoomReportController {

@Autowired
 private LiveRoomReportService liveroomreportservice;


@GetMapping
("/Report/{id}/LiveRoom/getLiveRoom")
public LiveRoom getLiveRoom(@PathVariable(name="id") String idWHGX){
return liveroomreportservice.getLiveRoom(idWHGX);
}


@PutMapping
("/Report/{id}/LiveRoom/setLiveRoom")
public void setLiveRoom(@PathVariable(name="id") String idWHGX,@RequestBody LiveRoom liveRoom){
liveroomreportservice.setLiveRoom(idWHGX,liveRoom);
}


}