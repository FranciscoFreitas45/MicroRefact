package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicCivil;
public interface TCreepersPublicCivilRequest {

   public TCreepersPublicCivil addTCreepersPublicCivil(TCreepersPublicCivil TCreepersPublicCivil,Long id);
   public TCreepersPublicCivil removeTCreepersPublicCivil(TCreepersPublicCivil TCreepersPublicCivil,Long id);
   public List<TCreepersPublicCivil> getTCreepersPublicCivils(Long id);
   public void setTCreepersPublicCivils(List<TCreepersPublicCivil> TCreepersPublicCivils,Long id);
}