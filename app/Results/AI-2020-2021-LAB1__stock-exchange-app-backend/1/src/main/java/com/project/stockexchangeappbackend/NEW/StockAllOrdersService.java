package com.project.stockexchangeappbackend.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.repository.StockIndexValueRepository;
import com.project.stockexchangeappbackend.entity.Stock;
@Service
public class StockAllOrdersService {

@Autowired
 private StockIndexValueRepository stockindexvaluerepository;


}