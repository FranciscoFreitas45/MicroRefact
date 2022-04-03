package com.project.stockexchangeappbackend.Interface;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.Stock;
public interface StockRepository {

   public Optional<Stock> findByIdAndIsDeletedFalse(Long id);
}