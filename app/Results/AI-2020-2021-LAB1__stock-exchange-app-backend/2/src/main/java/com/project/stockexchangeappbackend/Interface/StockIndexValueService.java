package com.project.stockexchangeappbackend.Interface;
public interface StockIndexValueService {

   public List<StockIndexValueDTO> getStockIndexValues(Long stockId,Specification<StockIndexValue> specification,Integer interval);
}