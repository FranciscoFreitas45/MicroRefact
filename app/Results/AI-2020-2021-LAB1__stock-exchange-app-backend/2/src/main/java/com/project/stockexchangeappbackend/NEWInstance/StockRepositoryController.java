package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StockRepositoryController {

 private StockRepository stockrepository;


@GetMapping
("/findByIdAndIsDeletedFalse")
public Optional<Stock> findByIdAndIsDeletedFalse(@RequestParam(name = "id") Long id){
  return stockrepository.findByIdAndIsDeletedFalse(id);
}


}