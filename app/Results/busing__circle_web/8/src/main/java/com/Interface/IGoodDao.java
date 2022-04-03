package com.Interface;
public interface IGoodDao {

   public void addBuyNum(String goodId,int buyNum);
   public void batchAddBuyNum(List<Map<String,?>> paramListMap);
}