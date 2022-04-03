package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersCompensatory;
public interface TCreepersCompensatoryRequest {

   public void setTCreepersCompensatories(List<TCreepersCompensatory> TCreepersCompensatories,Long id);
   public TCreepersCompensatory removeTCreepersCompensatory(TCreepersCompensatory TCreepersCompensatory,Long id);
   public TCreepersCompensatory addTCreepersCompensatory(TCreepersCompensatory TCreepersCompensatory,Long id);
   public List<TCreepersCompensatory> getTCreepersCompensatories(Long id);
}