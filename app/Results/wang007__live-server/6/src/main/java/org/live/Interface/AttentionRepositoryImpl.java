package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.AttentionRepository;
public class AttentionRepositoryImpl implements AttentionRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public long countAttentionsByLiveRoom_Id(String liveRoomId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countAttentionsByLiveRoom_Id"))
    .queryParam("liveRoomId",liveRoomId)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


public List<Attention> findAttentionsByUser_IdAndLiveRoom_Id(String userId,String liveRoomId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAttentionsByUser_IdAndLiveRoom_Id"))
    .queryParam("userId",userId)
    .queryParam("liveRoomId",liveRoomId)
;  List<Attention> aux = restTemplate.getForObject(builder.toUriString(), List<Attention>.class);

 return aux;
}


}