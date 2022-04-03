package com.tech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.Interface.IChatroomMembersService;
public class IChatroomMembersServiceImpl implements IChatroomMembersService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public boolean checkIfMemberExistsInChatroom(Long member_id,Long room_id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkIfMemberExistsInChatroom"))
    .queryParam("member_id",member_id)
    .queryParam("room_id",room_id)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}