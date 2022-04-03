package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.LiveRoom;
import org.live.Request.LiveRoomRequest;
public class LiveRoomRequestImpl implements LiveRoomRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public LiveRoom getLiveRoom(String idWHGX){
 LiveRoom aux = restTemplate.getForObject("http://7/Report/{id}/LiveRoom/getLiveRoom",LiveRoom.class,idWHGX);
return aux;
}


public void setLiveRoom(LiveRoom liveRoom,String idWHGX){
 restTemplate.put("http://7/Report/{id}/LiveRoom/setLiveRoom",liveRoom,idWHGX);
 return ;
}


}