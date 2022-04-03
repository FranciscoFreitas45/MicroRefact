package com.gs.Interface;
public interface IncomingTypeService {

   public Object count(Object Object);
   public Object queryByPager(Object Object);
   public int countByDisable();
   public List<IncomingType> queryByPagerDisable(Pager pager);
   public Object inactive(Object Object);
   public Object active(Object Object);
   public Object insert(Object Object);
   public IncomingType queryById(String inTypeName,String inTypeId);
   public Object update(Object Object);
   public Object queryAll(Object Object);
}