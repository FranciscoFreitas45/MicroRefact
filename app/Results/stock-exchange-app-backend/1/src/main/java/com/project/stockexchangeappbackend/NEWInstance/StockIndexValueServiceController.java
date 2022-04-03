package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
  import com.project.stockexchangeappbackend.service.StockIndexValueService;
 import com.project.stockexchangeappbackend.entity.Stock;
import  org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;
import com.project.stockexchangeappbackend.dto.StockIndexValueDTO;
import java.util.*;
import com.project.stockexchangeappbackend.entity.StockIndexValue;
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