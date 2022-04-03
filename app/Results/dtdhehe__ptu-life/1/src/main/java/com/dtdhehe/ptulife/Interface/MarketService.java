package com.dtdhehe.ptulife.Interface;
public interface MarketService {

   public Page<Market> queryGoodsById(String id,String goodsName,Pageable pageable);
   public void delGoodsById(String id);
}