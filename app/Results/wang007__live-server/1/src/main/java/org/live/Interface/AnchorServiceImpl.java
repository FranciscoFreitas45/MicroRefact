package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.AnchorService;
public class AnchorServiceImpl implements AnchorService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public AppAnchorInfo findAnchorForAppUser(String userId,String liveRoomId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAnchorForAppUser"))
    .queryParam("userId",userId)
    .queryParam("liveRoomId",liveRoomId)
;  AppAnchorInfo aux = restTemplate.getForObject(builder.toUriString(), AppAnchorInfo.class);

 return aux;
}


}