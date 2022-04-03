package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StockRepositoryController {

 private StockRepository stockrepository;


@GetMapping
("/findByStockType")
public List<Stock> findByStockType(@RequestParam(name = "stockType") StockType stockType){
  return stockrepository.findByStockType(stockType);
}


}