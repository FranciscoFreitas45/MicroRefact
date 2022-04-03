package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StockServiceController {

 private StockService stockservice;


@GetMapping
("/getAllStocks")
public List<Stock> getAllStocks(){
  return stockservice.getAllStocks();
}


@PutMapping
("/updateStocks")
public void updateStocks(@RequestParam(name = "stocks") Collection<Stock> stocks){
stockservice.updateStocks(stocks);
}


@GetMapping
("/parallelStream")
public Object parallelStream(@RequestParam(name = "Object") Object Object){
  return stockservice.parallelStream(Object);
}


@GetMapping
("/peek")
public Object peek(@RequestParam(name = "Object") Object Object){
  return stockservice.peek(Object);
}


}