package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicEnforcement;
public interface TCreepersPublicEnforcementRequest {

   public void setTCreepersPublicEnforcements(List<TCreepersPublicEnforcement> TCreepersPublicEnforcements,Long id);
   public List<TCreepersPublicEnforcement> getTCreepersPublicEnforcements(Long id);
   public TCreepersPublicEnforcement removeTCreepersPublicEnforcement(TCreepersPublicEnforcement TCreepersPublicEnforcement,Long id);
   public TCreepersPublicEnforcement addTCreepersPublicEnforcement(TCreepersPublicEnforcement TCreepersPublicEnforcement,Long id);
}