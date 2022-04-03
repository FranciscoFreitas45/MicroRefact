package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Place;
import com.ushahidi.swiftriver.core.Request.PlaceRequest;
public class PlaceRequestImpl implements PlaceRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Place getPlace(long idEIIX){
 Place aux = restTemplate.getForObject("http://3/BucketDropPlace/{id}/Place/getPlace",Place.class,idEIIX);
return aux;
}


public void setPlace(Place place,long idEIIX){
 restTemplate.put("http://3/BucketDropPlace/{id}/Place/setPlace",place,idEIIX);
 return ;
}


}