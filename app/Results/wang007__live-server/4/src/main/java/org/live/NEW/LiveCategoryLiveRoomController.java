package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.entity.LiveCategory;
@RestController
@CrossOrigin
public class LiveCategoryLiveRoomController {

@Autowired
 private LiveCategoryLiveRoomService livecategoryliveroomservice;


@GetMapping
("/LiveRoom/{id}/LiveCategory/getLiveCategory")
public LiveCategory getLiveCategory(@PathVariable(name="id") String idW6JM){
return livecategoryliveroomservice.getLiveCategory(idW6JM);
}


@PutMapping
("/LiveRoom/{id}/LiveCategory/setLiveCategory")
public void setLiveCategory(@PathVariable(name="id") String idW6JM,@RequestBody LiveCategory liveCategory){
livecategoryliveroomservice.setLiveCategory(idW6JM,liveCategory);
}


}