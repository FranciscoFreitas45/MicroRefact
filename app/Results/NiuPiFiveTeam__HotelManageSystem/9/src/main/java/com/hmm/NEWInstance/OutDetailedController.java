package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OutDetailedController {

 private OutDetailedRepository outdetailedrepository;


@PutMapping
("/setAmount/{id}")
public void setAmount(@PathVariable(name = "id") Long id,@RequestParam(name = "amount") float amount){
 outdetailedrepository.setAmount(id,amount);
}


@PutMapping
("/setGoodsName/{id}")
public void setGoodsName(@PathVariable(name = "id") Long id,@RequestParam(name = "goodsName") String goodsName){
 outdetailedrepository.setGoodsName(id,goodsName);
}


@PutMapping
("/setGoodsNo/{id}")
public void setGoodsNo(@PathVariable(name = "id") Long id,@RequestParam(name = "goodsNo") String goodsNo){
 outdetailedrepository.setGoodsNo(id,goodsNo);
}


@PutMapping
("/setOutStorage/{id}")
public void setOutStorage(@PathVariable(name = "id") Long id,@RequestParam(name = "outStorage") OutStorage outStorage){
 outdetailedrepository.setOutStorage(id,outStorage);
}


}