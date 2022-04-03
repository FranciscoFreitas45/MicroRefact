package com.hmm.Interface;
public interface StockRepository {

   public List<Stock> findByStockType(StockType stockType);
}