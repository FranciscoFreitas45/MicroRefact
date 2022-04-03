package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaPlaceDao;
import com.ushahidi.swiftriver.core.model.Place;
@Service
public class PlaceRiverDropPlaceService {

@Autowired
 private JpaPlaceDao jpaplacedao;


public Place getPlace(long id6ANM){
return jpaplacedao.getPlace(id6ANM);
}


public void setPlace(long id6ANM,Place place){
jpaplacedao.setPlace(id6ANM,place);
}


}