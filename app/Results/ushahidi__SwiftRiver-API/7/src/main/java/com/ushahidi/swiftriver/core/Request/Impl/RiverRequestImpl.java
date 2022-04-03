package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.River;
import com.ushahidi.swiftriver.core.Request.RiverRequest;
public class RiverRequestImpl implements RiverRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<River> getRivers(long id){
 List<River> aux = restTemplate.getForObject("http://2/Account/{id}/River/getRivers",List<River>.class,id);
return aux;
}


public void setFollowingRivers(List<River> followingRivers,long id){
 restTemplate.put("http://2/Account/{id}/River/setFollowingRivers",followingRivers,id);
 return ;
}


public void setCollaboratingRivers(List<River> collaboratingRivers,long id){
 restTemplate.put("http://2/Account/{id}/River/setCollaboratingRivers",collaboratingRivers,id);
 return ;
}


public void setRivers(List<River> rivers,long id){
 restTemplate.put("http://2/Account/{id}/River/setRivers",rivers,id);
 return ;
}


public List<River> getCollaboratingRivers(long id){
 List<River> aux = restTemplate.getForObject("http://2/Account/{id}/River/getCollaboratingRivers",List<River>.class,id);
return aux;
}


public List<River> getFollowingRivers(long id){
 List<River> aux = restTemplate.getForObject("http://2/Account/{id}/River/getFollowingRivers",List<River>.class,id);
return aux;
}


}