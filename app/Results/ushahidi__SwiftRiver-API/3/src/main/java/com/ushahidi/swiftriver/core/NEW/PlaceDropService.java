package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaPlaceDao;
import com.ushahidi.swiftriver.core.model.Place;
@Service
public class PlaceDropService {

@Autowired
 private JpaPlaceDao jpaplacedao;


public List<Place> getPlaces(long id){
return jpaplacedao.getPlaces(id);
}


public void setPlaces(long id,List<Place> places){
jpaplacedao.setPlaces(id,places);
}


}