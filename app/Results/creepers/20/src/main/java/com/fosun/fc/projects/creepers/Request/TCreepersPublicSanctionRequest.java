package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicSanction;
public interface TCreepersPublicSanctionRequest {

   public void setTCreepersPublicSanctions(List<TCreepersPublicSanction> TCreepersPublicSanctions,Long id);
   public List<TCreepersPublicSanction> getTCreepersPublicSanctions(Long id);
   public TCreepersPublicSanction removeTCreepersPublicSanction(TCreepersPublicSanction TCreepersPublicSanction,Long id);
   public TCreepersPublicSanction addTCreepersPublicSanction(TCreepersPublicSanction TCreepersPublicSanction,Long id);
}