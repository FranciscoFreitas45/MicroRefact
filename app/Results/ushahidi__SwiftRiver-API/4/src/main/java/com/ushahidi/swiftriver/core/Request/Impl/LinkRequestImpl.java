package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Link;
import com.ushahidi.swiftriver.core.Request.LinkRequest;
public class LinkRequestImpl implements LinkRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setLink(Link link,long idOZP4){
 restTemplate.put("http://3/BucketDropLink/{id}/Link/setLink",link,idOZP4);
 return ;
}


public Link getLink(long idOZP4){
 Link aux = restTemplate.getForObject("http://3/BucketDropLink/{id}/Link/getLink",Link.class,idOZP4);
return aux;
}


}