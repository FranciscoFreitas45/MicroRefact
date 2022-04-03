package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.LiveRoomRepository;
public class LiveRoomRepositoryImpl implements LiveRoomRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public long countLiveRoomByLiveCategory(String liveCategoryId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countLiveRoomByLiveCategory"))
    .queryParam("liveCategoryId",liveCategoryId)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


}