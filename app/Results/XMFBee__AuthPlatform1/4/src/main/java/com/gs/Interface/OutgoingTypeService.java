package com.gs.Interface;
public interface OutgoingTypeService {

   public Object count(Object Object);
   public Object queryByPager(Object Object);
   public int countByDisable();
   public List<OutgoingType> queryByPagerDisable(Pager pager);
   public Object inactive(Object Object);
   public Object active(Object Object);
   public Object insert(Object Object);
   public OutgoingType queryById(String outTypeName,String outTypeId);
   public Object update(Object Object);
   public Object queryAll(Object Object);
}