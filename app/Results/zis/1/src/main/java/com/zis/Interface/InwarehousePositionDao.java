package com.zis.Interface;
public interface InwarehousePositionDao {

   public Object save(Object Object);
   public List<InwarehousePosition> findAvailablePosition(Integer inwarehouseId);
}