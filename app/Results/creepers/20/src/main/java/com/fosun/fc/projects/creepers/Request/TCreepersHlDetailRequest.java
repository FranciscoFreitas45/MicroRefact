package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersHlDetail;
public interface TCreepersHlDetailRequest {

   public TCreepersHlDetail addTCreepersHlDetail(TCreepersHlDetail TCreepersHlDetail,Long id);
   public List<TCreepersHlDetail> getTCreepersHlDetails(Long id);
   public void setTCreepersHlDetails(List<TCreepersHlDetail> TCreepersHlDetails,Long id);
   public TCreepersHlDetail removeTCreepersHlDetail(TCreepersHlDetail TCreepersHlDetail,Long id);
}