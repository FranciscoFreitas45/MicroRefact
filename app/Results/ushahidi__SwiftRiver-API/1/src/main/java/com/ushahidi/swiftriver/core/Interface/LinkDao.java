package com.ushahidi.swiftriver.core.Interface;
public interface LinkDao {

   public Link findByHash(String hash);
   public Object create(Object Object);
   public Object findById(Object Object);
}