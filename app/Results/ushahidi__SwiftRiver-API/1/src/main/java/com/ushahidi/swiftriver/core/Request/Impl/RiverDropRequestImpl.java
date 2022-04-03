package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.RiverDrop;
import com.ushahidi.swiftriver.core.Request.RiverDropRequest;
public class RiverDropRequestImpl implements RiverDropRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setDrops(List<RiverDrop> drops,long id){
 restTemplate.put("http://2/Channel/{id}/RiverDrop/setDrops",drops,id);
 return ;
}


public List<RiverDrop> getDrops(long id){
 List<RiverDrop> aux = restTemplate.getForObject("http://2/Channel/{id}/RiverDrop/getDrops",List<RiverDrop>.class,id);
return aux;
}


}