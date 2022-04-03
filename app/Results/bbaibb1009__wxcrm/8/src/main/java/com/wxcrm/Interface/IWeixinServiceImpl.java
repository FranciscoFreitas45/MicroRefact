package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IWeixinService;
public class IWeixinServiceImpl implements IWeixinService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public LzWeiEnter getWeiEnterByAppId(String appId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeiEnterByAppId"))
    .queryParam("appId",appId)
;  LzWeiEnter aux = restTemplate.getForObject(builder.toUriString(), LzWeiEnter.class);

 return aux;
}


public String getCurrentAccessTokenStr(Integer wecId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCurrentAccessTokenStr"))
    .queryParam("wecId",wecId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public LzWeiEnter getWeiEnterById(Integer weiEnterId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeiEnterById"))
    .queryParam("weiEnterId",weiEnterId)
;  LzWeiEnter aux = restTemplate.getForObject(builder.toUriString(), LzWeiEnter.class);

 return aux;
}


}