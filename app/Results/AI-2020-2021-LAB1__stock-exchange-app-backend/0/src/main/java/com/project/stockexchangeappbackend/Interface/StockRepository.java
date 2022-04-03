package com.project.stockexchangeappbackend.Interface;
public interface StockRepository {

   public Optional<Stock> findByIdAndIsDeletedFalse(Long id);
}