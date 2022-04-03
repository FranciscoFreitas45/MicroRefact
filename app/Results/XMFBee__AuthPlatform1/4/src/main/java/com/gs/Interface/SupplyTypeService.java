package com.gs.Interface;
public interface SupplyTypeService {

   public Object queryAll(Object Object);
   public Object count(Object Object);
   public Object queryByPager(Object Object);
   public int queryNameByOne(String supplyTypeName,String supplyTypeId);
   public int countByDisable();
   public List<SupplyType> queryByPagerDisable(Pager pager);
   public Object insert(Object Object);
   public Object update(Object Object);
   public Object inactive(Object Object);
   public Object active(Object Object);
}