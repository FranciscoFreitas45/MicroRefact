package com.project.stockexchangeappbackend.Interface;
import com.project.stockexchangeappbackend.DTO.Stock;
import java.util.*;
public interface StockRepository {

   public Optional<Stock> findByIdAndIsDeletedFalse(Long id);
}