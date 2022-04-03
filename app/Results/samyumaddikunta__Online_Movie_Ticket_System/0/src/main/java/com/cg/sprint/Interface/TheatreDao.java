package com.cg.sprint.Interface;
public interface TheatreDao {

   public Object save(Object Object);
   public List<Theatre> getTheatreList();
   public Object existsById(Object Object);
   public Object deleteById(Object Object);
}