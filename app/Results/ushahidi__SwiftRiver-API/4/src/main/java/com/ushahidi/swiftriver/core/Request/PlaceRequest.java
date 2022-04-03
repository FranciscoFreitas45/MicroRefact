package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Place;
public interface PlaceRequest {

   public Place getPlace(long idEIIX);
   public void setPlace(Place place,long idEIIX);
}