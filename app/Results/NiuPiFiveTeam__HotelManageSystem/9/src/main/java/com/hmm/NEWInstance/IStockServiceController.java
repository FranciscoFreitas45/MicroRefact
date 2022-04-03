package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IStockServiceController {

 private IStockService istockservice;


@GetMapping
("/findByGoodsNo")
public Stock findByGoodsNo(@RequestParam(name = "goodsNo") String goodsNo){
  return istockservice.findByGoodsNo(goodsNo);
}


@GetMapping
("/save")
public Stock save(@RequestParam(name = "entity") Stock entity){
  return istockservice.save(entity);
}


@GetMapping
("/findByStockType")
public List<DailyNecessaryDto> findByStockType(){
  return istockservice.findByStockType();
}


}