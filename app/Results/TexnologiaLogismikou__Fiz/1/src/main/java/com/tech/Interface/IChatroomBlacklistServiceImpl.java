package com.tech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.Interface.IChatroomBlacklistService;
public class IChatroomBlacklistServiceImpl implements IChatroomBlacklistService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public ChatroomBlacklist findByRoomIDAndRoomMember(Long room_id,Long room_member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRoomIDAndRoomMember"))
    .queryParam("room_id",room_id)
    .queryParam("room_member",room_member)
;  ChatroomBlacklist aux = restTemplate.getForObject(builder.toUriString(), ChatroomBlacklist.class);

 return aux;
}


public void setNewTime(Long room_id,Long member_id,Date room_expiration_time){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNewTime"))
    .queryParam("room_id",room_id)
    .queryParam("member_id",member_id)
    .queryParam("room_expiration_time",room_expiration_time)
;
  restTemplate.put(builder.toUriString(), null);
}


public boolean delete(ChatroomBlacklist deleteRecord){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("deleteRecord",deleteRecord)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void add(ChatroomBlacklist newRecord){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))
    .queryParam("newRecord",newRecord)
;
  restTemplate.put(builder.toUriString(), null);
}


}