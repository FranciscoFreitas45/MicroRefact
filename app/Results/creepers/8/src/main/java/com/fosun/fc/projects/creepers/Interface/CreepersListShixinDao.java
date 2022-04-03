package com.fosun.fc.projects.creepers.Interface;
public interface CreepersListShixinDao {

   public List<TCreepersListShixin> queryListByMerName(String merName);
   public Object findAll(Object Object);
   public Object save(Object Object);
   public void updateListByMerName(String merName,String flag);
}