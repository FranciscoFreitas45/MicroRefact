package com.ushahidi.swiftriver.core.Interface;
public interface TagDao {

   public Tag findByHash(String hash);
   public Object create(Object Object);
   public Object findById(Object Object);
}