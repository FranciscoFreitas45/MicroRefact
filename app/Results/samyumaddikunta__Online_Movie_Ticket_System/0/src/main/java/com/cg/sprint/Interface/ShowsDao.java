package com.cg.sprint.Interface;
public interface ShowsDao {

   public Object save(Object Object);
   public List<Shows> getShowList();
   public Object existsById(Object Object);
   public Object deleteById(Object Object);
}