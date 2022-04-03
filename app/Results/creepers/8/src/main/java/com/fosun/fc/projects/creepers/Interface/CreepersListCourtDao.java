package com.fosun.fc.projects.creepers.Interface;
public interface CreepersListCourtDao {

   public List<TCreepersListCourt> queryListByMerName(String merName);
   public Object findAll(Object Object);
   public Object save(Object Object);
   public void updateListByMerName(String merName,String flag);
}