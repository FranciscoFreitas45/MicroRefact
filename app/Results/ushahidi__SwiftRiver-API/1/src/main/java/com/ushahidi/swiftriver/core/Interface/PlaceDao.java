package com.ushahidi.swiftriver.core.Interface;
public interface PlaceDao {

   public Place findByHash(String hash);
   public Object create(Object Object);
   public Object findById(Object Object);
}