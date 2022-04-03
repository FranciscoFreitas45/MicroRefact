package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StockController {

 private StockRepository stockrepository;


@PutMapping
("/setAmount/{id}")
public void setAmount(@PathVariable(name = "id") Long id,@RequestParam(name = "amount") float amount){
 stockrepository.setAmount(id,amount);
}


}