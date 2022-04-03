package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Place;
public interface PlaceRequest {

   public List<Place> getPlaces(long id);
   public void setPlaces(List<Place> places,long id);
}