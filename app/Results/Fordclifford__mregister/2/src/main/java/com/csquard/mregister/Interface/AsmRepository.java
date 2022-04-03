package com.csquard.mregister.Interface;
public interface AsmRepository {

   public Object findById(Object Object);
   public Boolean existsBySalesRegionId(Long salesRegionId);
   public Object save(Object Object);
}