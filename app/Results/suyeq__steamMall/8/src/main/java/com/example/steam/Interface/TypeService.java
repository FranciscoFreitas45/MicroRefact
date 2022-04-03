package com.example.steam.Interface;
public interface TypeService {

   public List<String> findAllType();
   public int deleteTypeById(long typeId);
   public int addType(String typeName);
}