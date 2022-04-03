package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersGuarantee;
public interface TCreepersGuaranteeRequest {

   public void setTCreepersGuarantees(List<TCreepersGuarantee> TCreepersGuarantees,Long id);
   public TCreepersGuarantee addTCreepersGuarantee(TCreepersGuarantee TCreepersGuarantee,Long id);
   public List<TCreepersGuarantee> getTCreepersGuarantees(Long id);
   public TCreepersGuarantee removeTCreepersGuarantee(TCreepersGuarantee TCreepersGuarantee,Long id);
}