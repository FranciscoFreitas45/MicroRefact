package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IBatchSellServiceController {

 private IBatchSellService ibatchsellservice;


@GetMapping
("/queryCurrentBatchSell")
public BatchSell queryCurrentBatchSell(){
  return ibatchsellservice.queryCurrentBatchSell();
}


@GetMapping
("/queryCurrentSellGoodsId")
public String queryCurrentSellGoodsId(@RequestParam(name = "batchId") int batchId){
  return ibatchsellservice.queryCurrentSellGoodsId(batchId);
}


}