package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.River;
import com.ushahidi.swiftriver.core.Request.RiverRequest;
public class RiverRequestImpl implements RiverRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public River getActionOnObj(Long id37M8){
 River aux = restTemplate.getForObject("http://2/RiverActivity/{id}/River/getActionOnObj",River.class,id37M8);
return aux;
}


public void setActionOnObj(River actionOnObj,Long id37M8){
 restTemplate.put("http://2/RiverActivity/{id}/River/setActionOnObj",actionOnObj,id37M8);
 return ;
}


}