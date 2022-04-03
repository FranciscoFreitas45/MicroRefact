package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.River;
import com.ushahidi.swiftriver.core.Request.RiverRequest;
public class RiverRequestImpl implements RiverRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public River getRiver(Long idNMZI){
 River aux = restTemplate.getForObject("http://2/Rule/{id}/River/getRiver",River.class,idNMZI);
return aux;
}


public void setRiver(River river,Long idNMZI){
 restTemplate.put("http://2/Rule/{id}/River/setRiver",river,idNMZI);
 return ;
}


}