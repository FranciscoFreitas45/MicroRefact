package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IWebsiteService;
public class IWebsiteServiceImpl implements IWebsiteService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public WcWebsite queryMySiteByAdmin(Integer wadId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryMySiteByAdmin"))
    .queryParam("wadId",wadId)
;  WcWebsite aux = restTemplate.getForObject(builder.toUriString(), WcWebsite.class);

 return aux;
}


}