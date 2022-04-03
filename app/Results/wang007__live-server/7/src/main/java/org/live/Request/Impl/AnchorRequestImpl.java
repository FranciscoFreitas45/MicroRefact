package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.Anchor;
import org.live.Request.AnchorRequest;
public class AnchorRequestImpl implements AnchorRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Anchor getAnchor(String idFK1I){
 Anchor aux = restTemplate.getForObject("http://6/LiveRoom/{id}/Anchor/getAnchor",Anchor.class,idFK1I);
return aux;
}


public void setAnchor(Anchor anchor,String idFK1I){
 restTemplate.put("http://6/LiveRoom/{id}/Anchor/setAnchor",anchor,idFK1I);
 return ;
}


}