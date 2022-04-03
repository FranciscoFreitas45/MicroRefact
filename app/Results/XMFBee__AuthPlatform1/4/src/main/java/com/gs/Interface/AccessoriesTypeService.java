package com.gs.Interface;
public interface AccessoriesTypeService {

   public Object queryAll(Object Object);
   public Object count(Object Object);
   public Object queryByPager(Object Object);
   public Object insert(Object Object);
   public Object update(Object Object);
   public int countByDisable();
   public Object queryByPagerDisable(Object Object);
   public Object inactive(Object Object);
   public Object active(Object Object);
   public Object blurredQuery(Object Object);
   public Object countByBlurred(Object Object);
   public List<AccessoriesType> queryTypeName(String companyId);
   public int queryAccTypeNameOne(String accTypeName,String accTypeId);
}