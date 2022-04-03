package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Place;
import com.ushahidi.swiftriver.core.Request.PlaceRequest;
public class PlaceRequestImpl implements PlaceRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Place getPlace(long id6ANM){
 Place aux = restTemplate.getForObject("http://3/RiverDropPlace/{id}/Place/getPlace",Place.class,id6ANM);
return aux;
}


public void setPlace(Place place,long id6ANM){
 restTemplate.put("http://3/RiverDropPlace/{id}/Place/setPlace",place,id6ANM);
 return ;
}


}