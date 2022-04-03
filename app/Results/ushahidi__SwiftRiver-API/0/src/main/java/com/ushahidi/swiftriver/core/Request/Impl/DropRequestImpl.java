package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Drop;
import com.ushahidi.swiftriver.core.Request.DropRequest;
public class DropRequestImpl implements DropRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setDrops(List<Drop> drops,long id){
 restTemplate.put("http://6/Identity/{id}/Drop/setDrops",drops,id);
 return ;
}


public List<Drop> getDrops(long id){
 List<Drop> aux = restTemplate.getForObject("http://6/Identity/{id}/Drop/getDrops",List<Drop>.class,id);
return aux;
}


}