package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.LiveRoomService;
public class LiveRoomServiceImpl implements LiveRoomService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public LiveRoom findLiveRoomByMobileUser(MobileUser user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findLiveRoomByMobileUser"))
    .queryParam("user",user)
;  LiveRoom aux = restTemplate.getForObject(builder.toUriString(), LiveRoom.class);

 return aux;
}


public LiveRoom getLiveRoomByRoomNum(String roomNum){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLiveRoomByRoomNum"))
    .queryParam("roomNum",roomNum)
;  LiveRoom aux = restTemplate.getForObject(builder.toUriString(), LiveRoom.class);

 return aux;
}


public void reportLiveRoom(String userId,String liveRoomId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reportLiveRoom"))
    .queryParam("userId",userId)
    .queryParam("liveRoomId",liveRoomId)
;
  restTemplate.put(builder.toUriString(), null);
}


}