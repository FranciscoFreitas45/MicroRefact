package com.gs.Interface;
public interface AccessoriesService {

   public Object queryAll(Object Object);
   public Object count(Object Object);
   public List<Accessories> queryByIdPager(String id,Pager pager);
   public Object queryByPager(Object Object);
   public Object insert(Object Object);
   public Object update(Object Object);
   public Object countByDisable(Object Object);
   public Object queryByPagerDisable(Object Object);
   public Object inactive(Object Object);
   public Object active(Object Object);
   public Object blurredQuery(Object Object);
   public Object countByBlurred(Object Object);
   public Object queryById(Object Object);
   public List<Accessories> queryByCondition(String start,String end,String companyId,String accTypeId,String type);
}