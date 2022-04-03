package com.Interface;
public interface IGoodDao {

   public List<Map<String,Object>> queryGoodList(Page page);
   public List<Map<String,Object>> queryCurrentBatchGoodList(Page page,int batchId);
   public Map<String,Object> queryGood(String id);
   public List<Map<String,Object>> queryGoodTypeArgValues(String id);
   public List<Map<String,Object>> queryGoodTypeAttrValues(String id);
   public List<Map<String,Object>> queryImageList(String id);
   public List<Map<String,Object>> queryGoodComment(Page page);
}