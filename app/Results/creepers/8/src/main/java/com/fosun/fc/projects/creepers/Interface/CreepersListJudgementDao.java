package com.fosun.fc.projects.creepers.Interface;
public interface CreepersListJudgementDao {

   public List<TCreepersListJudgement> queryListByMerName(String merName);
   public Object findAll(Object Object);
   public Object save(Object Object);
   public void updateListByMerName(String merName,String flag);
}