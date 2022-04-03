package com.Interface;
public interface IGoodService {

   public List<Map<String,Object>> queryCurrentBatchGoodList(Page page,int batchId);
}