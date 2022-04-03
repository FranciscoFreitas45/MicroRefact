package com.fosun.fc.projects.creepers.Interface;
public interface CreepersListFundDao {

   public List<TCreepersListFund> queryListByUserCode(String userCode);
   public Object findAll(Object Object);
   public TCreepersListFund findTop1ByUserCode(String userCode);
   public void updateListByUserCode(String userCode,String flag);
}