package com.project.stockexchangeappbackend.Interface;
import java.util.*;
import com.project.stockexchangeappbackend.entity.Stock;


public interface StockService {

   public List<Stock> getAllStocks();
   public Object parallelStream(Object Object);
   public Object peek(Object Object);
   public void updateStocks(Collection<Stock> stocks);
}