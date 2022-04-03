package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.UserConnectionService;
public class UserConnectionServiceImpl implements UserConnectionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<AddFriendVo> getMyConnection(MemberSessionVo memberSessionVo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMyConnection"))
    .queryParam("memberSessionVo",memberSessionVo)
;  List<AddFriendVo> aux = restTemplate.getForObject(builder.toUriString(), List<AddFriendVo>.class);

 return aux;
}


public List<AddFriendVo> getConnectionToMe(Integer weUserIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getConnectionToMe"))
    .queryParam("weUserIdx",weUserIdx)
;  List<AddFriendVo> aux = restTemplate.getForObject(builder.toUriString(), List<AddFriendVo>.class);

 return aux;
}


public int addWeFriends(String arrayCheckId,Integer weUserIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addWeFriends"))
    .queryParam("arrayCheckId",arrayCheckId)
    .queryParam("weUserIdx",weUserIdx)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}