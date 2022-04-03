package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersOlDetail;
public interface TCreepersOlDetailRequest {

   public List<TCreepersOlDetail> getTCreepersOlDetails(Long id);
   public TCreepersOlDetail removeTCreepersOlDetail(TCreepersOlDetail TCreepersOlDetail,Long id);
   public void setTCreepersOlDetails(List<TCreepersOlDetail> TCreepersOlDetails,Long id);
   public TCreepersOlDetail addTCreepersOlDetail(TCreepersOlDetail TCreepersOlDetail,Long id);
}