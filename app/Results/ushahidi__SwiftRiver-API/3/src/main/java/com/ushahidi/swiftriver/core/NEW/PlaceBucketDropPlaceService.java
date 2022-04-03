package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaPlaceDao;
import com.ushahidi.swiftriver.core.model.Place;
@Service
public class PlaceBucketDropPlaceService {

@Autowired
 private JpaPlaceDao jpaplacedao;


public Place getPlace(long idEIIX){
return jpaplacedao.getPlace(idEIIX);
}


public void setPlace(long idEIIX,Place place){
jpaplacedao.setPlace(idEIIX,place);
}


}