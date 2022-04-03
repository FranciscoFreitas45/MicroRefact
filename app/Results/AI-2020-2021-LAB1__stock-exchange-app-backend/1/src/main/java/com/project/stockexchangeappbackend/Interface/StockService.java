package com.project.stockexchangeappbackend.Interface;
public interface StockService {

   public List<Stock> getAllStocks();
   public Object parallelStream(Object Object);
   public Object peek(Object Object);
   public void updateStocks(Collection<Stock> stocks);
}