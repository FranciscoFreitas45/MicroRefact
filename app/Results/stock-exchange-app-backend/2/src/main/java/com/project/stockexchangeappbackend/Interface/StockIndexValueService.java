package com.project.stockexchangeappbackend.Interface;
import com.project.stockexchangeappbackend.DTO.StockIndexValueDTO;
import org.springframework.data.jpa.domain.*;
import com.project.stockexchangeappbackend.DTO.StockIndexValue;
import java.util.*;
public interface StockIndexValueService {

   public List<StockIndexValueDTO> getStockIndexValues(Long stockId,Specification<StockIndexValue> specification,Integer interval);
}