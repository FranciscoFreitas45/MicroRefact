package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicIsp;
public interface TCreepersPublicIspRequest {

   public TCreepersPublicIsp addTCreepersPublicIsp(TCreepersPublicIsp TCreepersPublicIsp,Long id);
   public List<TCreepersPublicIsp> getTCreepersPublicIsps(Long id);
   public TCreepersPublicIsp removeTCreepersPublicIsp(TCreepersPublicIsp TCreepersPublicIsp,Long id);
   public void setTCreepersPublicIsps(List<TCreepersPublicIsp> TCreepersPublicIsps,Long id);
}