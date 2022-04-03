package com.Interface;
public interface IBatchSellService {

   public BatchSell queryCurrentBatchSell();
   public String queryCurrentSellGoodsId(int batchId);
}