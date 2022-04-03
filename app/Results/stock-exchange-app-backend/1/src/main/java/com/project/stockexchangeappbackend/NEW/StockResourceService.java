package com.project.stockexchangeappbackend.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.repository.StockRepository;
import com.project.stockexchangeappbackend.entity.Stock;
@Service
public class StockResourceService {

@Autowired
 private StockRepository stockrepository;


}