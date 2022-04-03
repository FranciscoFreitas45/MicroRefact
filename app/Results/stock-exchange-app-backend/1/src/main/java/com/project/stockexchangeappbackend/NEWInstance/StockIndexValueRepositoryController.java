package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.project.stockexchangeappbackend.repository.StockIndexValueRepository;
 import com.project.stockexchangeappbackend.entity.Stock;
@RestController
@CrossOrigin
public class StockIndexValueRepositoryController {

 private StockIndexValueRepository stockindexvaluerepository;


@PutMapping
("/deleteByStock")
public void deleteByStock(@RequestParam(name = "stock") Stock stock){
stockindexvaluerepository.deleteByStock(stock);
}


}