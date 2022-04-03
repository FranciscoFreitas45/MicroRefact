package com.ushahidi.swiftriver.core.Interface;
public interface ActivityDao {

   public Object create(Object Object);
   public List<Activity> find(long accountId,Integer count,Long lastId,Boolean newer,Boolean followers);
}