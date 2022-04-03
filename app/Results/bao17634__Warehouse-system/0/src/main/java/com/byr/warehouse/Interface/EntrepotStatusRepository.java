package com.byr.warehouse.Interface;
public interface EntrepotStatusRepository {

   public List<EntrepotStatus> findByEnterCodeAndMaterialCode(String enterCode,String materialCode);
   public Object delete(Object Object);
   public Object save(Object Object);
}