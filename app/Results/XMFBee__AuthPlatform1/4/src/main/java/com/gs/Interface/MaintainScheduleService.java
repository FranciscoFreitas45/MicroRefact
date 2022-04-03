package com.gs.Interface;
public interface MaintainScheduleService {

   public List<MaintainSchedule> queryScheduleByRecord(String recordId);
   public Object queryAll(Object Object);
   public Object count(Object Object);
   public Object queryByPager(Object Object);
   public Object insert(Object Object);
   public Object countByDisable(Object Object);
   public Object queryByPagerDisable(Object Object);
   public Object inactive(Object Object);
   public Object active(Object Object);
   public Object update(Object Object);
}