package com.project.stockexchangeappbackend.Interface;
import com.project.stockexchangeappbackend.DTO.Stock;
public interface StockIndexValueRepository {

   public void deleteByStock(Stock stock);
}