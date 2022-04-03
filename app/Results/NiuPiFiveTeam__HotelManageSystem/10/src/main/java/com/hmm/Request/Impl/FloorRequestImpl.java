package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Floor;
import com.hmm.Request.FloorRequest;
public class FloorRequestImpl implements FloorRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Floor getFloorNode(Long floorIdW7A6){
 Floor aux = restTemplate.getForObject("http://0/Room/{id}/Floor/getFloorNode",Floor.class,floorIdW7A6);
return aux;
}


public void setFloorNode(Floor floorNode,Long floorIdW7A6){
 restTemplate.put("http://0/Room/{id}/Floor/setFloorNode",floorNode,floorIdW7A6);
 return ;
}


}