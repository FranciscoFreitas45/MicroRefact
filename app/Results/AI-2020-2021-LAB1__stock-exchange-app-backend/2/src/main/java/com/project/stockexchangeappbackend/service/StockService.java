package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.dto.CreateStockDTO;
import com.project.stockexchangeappbackend.dto.EditStockNameDTO;
import com.project.stockexchangeappbackend.dto.UpdateStockAmountDTO;
import com.project.stockexchangeappbackend.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.util.Collection;
import java.util.List;
public interface StockService {


public Stock getStockByAbbreviation(String abbreviation)
;

public void createStock(CreateStockDTO stockDTO,String tag)
;

public void deleteStock(Long id)
;

public void updateStock(EditStockNameDTO stock,String id)
;

public void updateStocks(Collection<Stock> stocks)
;

public Stock getStockById(Long id)
;

public Page<Stock> getStocks(Pageable pageable,Specification<Stock> specification)
;

public void updateStockAmount(Long stockId,UpdateStockAmountDTO updateStockAmount)
;

public List<Stock> getAllStocks()
;

public Stock getStockByIdOrAbbreviation(String id)
;

}