package com.tech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.Interface.IChatroomPrivilegesService;
public class IChatroomPrivilegesServiceImpl implements IChatroomPrivilegesService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public void add(ChatroomPrivileges newRecord){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))
    .queryParam("newRecord",newRecord)
;
  restTemplate.put(builder.toUriString(), null);
}


public ChatroomPrivileges findByRoomId(Long room_id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRoomId"))
    .queryParam("room_id",room_id)
;  ChatroomPrivileges aux = restTemplate.getForObject(builder.toUriString(), ChatroomPrivileges.class);

 return aux;
}


public void setChatroomPrivileges(String room_privileges,Boolean room_password_protected,String room_password,String room_access_method,Long room_id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChatroomPrivileges"))
    .queryParam("room_privileges",room_privileges)
    .queryParam("room_password_protected",room_password_protected)
    .queryParam("room_password",room_password)
    .queryParam("room_access_method",room_access_method)
    .queryParam("room_id",room_id)
;
  restTemplate.put(builder.toUriString(), null);
}


}