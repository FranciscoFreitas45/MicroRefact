package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttentionRepositoryController {

 private AttentionRepository attentionrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return attentionrepository.save(Object);
}


@GetMapping
("/countAttentionsByLiveRoom_Id")
public long countAttentionsByLiveRoom_Id(@RequestParam(name = "liveRoomId") String liveRoomId){
  return attentionrepository.countAttentionsByLiveRoom_Id(liveRoomId);
}


@GetMapping
("/findAttentionsByUser_IdAndLiveRoom_Id")
public List<Attention> findAttentionsByUser_IdAndLiveRoom_Id(@RequestParam(name = "userId") String userId,@RequestParam(name = "liveRoomId") String liveRoomId){
  return attentionrepository.findAttentionsByUser_IdAndLiveRoom_Id(userId,liveRoomId);
}


}