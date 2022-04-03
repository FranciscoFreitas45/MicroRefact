package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersBasic;
public interface TCreepersBasicRequest {

   public List<TCreepersBasic> getTCreepersBasics(Long id);
   public TCreepersBasic addTCreepersBasic(TCreepersBasic TCreepersBasic,Long id);
   public void setTCreepersBasics(List<TCreepersBasic> TCreepersBasics,Long id);
   public TCreepersBasic removeTCreepersBasic(TCreepersBasic TCreepersBasic,Long id);
}