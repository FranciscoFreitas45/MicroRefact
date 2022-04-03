package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.RiverDrop;
import com.ushahidi.swiftriver.core.Request.RiverDropRequest;
public class RiverDropRequestImpl implements RiverDropRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<RiverDrop> getReadRiverDrops(long id){
 List<RiverDrop> aux = restTemplate.getForObject("http://2/Account/{id}/RiverDrop/getReadRiverDrops",List<RiverDrop>.class,id);
return aux;
}


public void setReadRiverDrops(List<RiverDrop> readRiverDrops,long id){
 restTemplate.put("http://2/Account/{id}/RiverDrop/setReadRiverDrops",readRiverDrops,id);
 return ;
}


}