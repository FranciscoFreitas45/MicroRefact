package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StockIndexValueServiceController {

 private StockIndexValueService stockindexvalueservice;


@GetMapping
("/getStockIndexValues")
public List<StockIndexValueDTO> getStockIndexValues(@RequestParam(name = "stockId") Long stockId,@RequestParam(name = "specification") Specification<StockIndexValue> specification,@RequestParam(name = "interval") Integer interval){
  return stockindexvalueservice.getStockIndexValues(stockId,specification,interval);
}


}