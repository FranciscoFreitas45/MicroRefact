package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Place;
public interface PlaceDao extends GenericDao<Place>{


public void getPlaces(List<Drop> drops)
;

public Place findByHash(String hash)
;

}