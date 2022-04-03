package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StockController {

 private Stock stock;

 private Stock stock;


@PutMapping
("/setAmount")
public void setAmount(@RequestParam(name = "amount") float amount){
stock.setAmount(amount);
}


}