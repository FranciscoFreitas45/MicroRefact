package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttentionController {

 private AttentionRepository attentionrepository;


@PutMapping
("/setUser/{id}")
public void setUser(@PathVariable(name = "id") String id,@RequestParam(name = "user") MobileUser user){
 attentionrepository.setUser(id,user);
}


@PutMapping
("/setLiveRoom/{id}")
public void setLiveRoom(@PathVariable(name = "id") String id,@RequestParam(name = "liveRoom") LiveRoom liveRoom){
 attentionrepository.setLiveRoom(id,liveRoom);
}


@PutMapping
("/setCreateTime/{id}")
public void setCreateTime(@PathVariable(name = "id") String id,@RequestParam(name = "createTime") Date createTime){
 attentionrepository.setCreateTime(id,createTime);
}


}