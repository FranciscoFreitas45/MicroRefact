package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.LifeStyle;
import org.jugbd.mnet.Request.LifeStyleRequest;
public class LifeStyleRequestImpl implements LifeStyleRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setLifeStyle(LifeStyle lifeStyle,Long id){
 restTemplate.put("http://12/Register/{id}/LifeStyle/setLifeStyle",lifeStyle,id);
 return ;
}


public LifeStyle getLifeStyle(Long id){
 LifeStyle aux = restTemplate.getForObject("http://12/Register/{id}/LifeStyle/getLifeStyle",LifeStyle.class,id);
return aux;
}


}