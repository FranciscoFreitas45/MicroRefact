package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Link;
import com.ushahidi.swiftriver.core.Request.LinkRequest;
public class LinkRequestImpl implements LinkRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Link getOriginalUrl(long idLCK8){
 Link aux = restTemplate.getForObject("http://3/Drop/{id}/Link/getOriginalUrl",Link.class,idLCK8);
return aux;
}


public void setOriginalUrl(Link originalUrl,long idLCK8){
 restTemplate.put("http://3/Drop/{id}/Link/setOriginalUrl",originalUrl,idLCK8);
 return ;
}


}