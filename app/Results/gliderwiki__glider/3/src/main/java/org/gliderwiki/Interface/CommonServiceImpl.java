package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.CommonService;
public class CommonServiceImpl implements CommonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public WeGroupInfo getWeGroupInfo(Integer groupIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeGroupInfo"))
    .queryParam("groupIdx",groupIdx)
;  WeGroupInfo aux = restTemplate.getForObject(builder.toUriString(), WeGroupInfo.class);

 return aux;
}


public List<WeUser> getWeUserList(Integer loginUserIdx,String userNick,String userEmail,String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeUserList"))
    .queryParam("loginUserIdx",loginUserIdx)
    .queryParam("userNick",userNick)
    .queryParam("userEmail",userEmail)
    .queryParam("userName",userName)
;  List<WeUser> aux = restTemplate.getForObject(builder.toUriString(), List<WeUser>.class);

 return aux;
}


public WeUser getUserInfo(Integer weUserIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserInfo"))
    .queryParam("weUserIdx",weUserIdx)
;  WeUser aux = restTemplate.getForObject(builder.toUriString(), WeUser.class);

 return aux;
}


public WeProfile getUserProfileInfo(Integer weUserIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserProfileInfo"))
    .queryParam("weUserIdx",weUserIdx)
;  WeProfile aux = restTemplate.getForObject(builder.toUriString(), WeProfile.class);

 return aux;
}


}