package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Place;
import com.ushahidi.swiftriver.core.Request.PlaceRequest;
public class PlaceRequestImpl implements PlaceRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Place> getPlaces(long id){
 List<Place> aux = restTemplate.getForObject("http://3/Drop/{id}/Place/getPlaces",List<Place>.class,id);
return aux;
}


public void setPlaces(List<Place> places,long id){
 restTemplate.put("http://3/Drop/{id}/Place/setPlaces",places,id);
 return ;
}


}