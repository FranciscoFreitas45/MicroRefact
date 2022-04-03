package com.gs.Interface;
public interface CarModelService {

   public Object queryAll(Object Object);
   public Object count(Object Object);
   public Object queryByPager(Object Object);
   public List<CarModel> queryByBrandId(String id);
   public Object insert(Object Object);
   public Object update(Object Object);
   public Object countByDisable(Object Object);
   public Object queryByPagerDisable(Object Object);
   public int querymodelName(String modelName,String modelId);
   public Object inactive(Object Object);
   public Object active(Object Object);
   public Object blurredQuery(Object Object);
   public Object countByBlurred(Object Object);
}