package com.fosun.fc.projects.creepers.Interface;
public interface CreepersListInsuranceDao {

   public List<TCreepersListInsurance> queryListByUserCode(String userCode);
   public Object findAll(Object Object);
   public TCreepersListInsurance findTop1ByUserCode(String userCode);
   public void updateListByUserCode(String userCode,String flag);
}