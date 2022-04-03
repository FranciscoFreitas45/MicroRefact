package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
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