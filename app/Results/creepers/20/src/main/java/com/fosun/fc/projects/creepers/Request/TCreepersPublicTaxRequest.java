package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicTax;
public interface TCreepersPublicTaxRequest {

   public List<TCreepersPublicTax> getTCreepersPublicTaxs(Long id);
   public void setTCreepersPublicTaxs(List<TCreepersPublicTax> TCreepersPublicTaxs,Long id);
   public TCreepersPublicTax addTCreepersPublicTax(TCreepersPublicTax TCreepersPublicTax,Long id);
   public TCreepersPublicTax removeTCreepersPublicTax(TCreepersPublicTax TCreepersPublicTax,Long id);
}