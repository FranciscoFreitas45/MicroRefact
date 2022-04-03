package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Room;
import com.hmm.Request.RoomRequest;
public class RoomRequestImpl implements RoomRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setChildNodes(List<Room> childNodes,Long floorId){
 restTemplate.put("http://10/Floor/{id}/Room/setChildNodes",childNodes,floorId);
 return ;
}


public List<Room> getChildNodes(Long floorId){
 List<Room> aux = restTemplate.getForObject("http://10/Floor/{id}/Room/getChildNodes",List<Room>.class,floorId);
return aux;
}


}