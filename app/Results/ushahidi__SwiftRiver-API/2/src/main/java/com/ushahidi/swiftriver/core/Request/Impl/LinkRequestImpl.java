package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Link;
import com.ushahidi.swiftriver.core.Request.LinkRequest;
public class LinkRequestImpl implements LinkRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setLink(Link link,long idM175){
 restTemplate.put("http://3/RiverDropLink/{id}/Link/setLink",link,idM175);
 return ;
}


public Link getLink(long idM175){
 Link aux = restTemplate.getForObject("http://3/RiverDropLink/{id}/Link/getLink",Link.class,idM175);
return aux;
}


}