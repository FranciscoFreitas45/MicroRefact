package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OutDetailedController {

 private OutDetailed outdetailed;

 private OutDetailed outdetailed;


@PutMapping
("/setAmount")
public void setAmount(@RequestParam(name = "amount") float amount){
outdetailed.setAmount(amount);
}


@PutMapping
("/setGoodsName")
public void setGoodsName(@RequestParam(name = "goodsName") String goodsName){
outdetailed.setGoodsName(goodsName);
}


@PutMapping
("/setGoodsNo")
public void setGoodsNo(@RequestParam(name = "goodsNo") String goodsNo){
outdetailed.setGoodsNo(goodsNo);
}


@PutMapping
("/setOutStorage")
public void setOutStorage(@RequestParam(name = "outStorage") OutStorage outStorage){
outdetailed.setOutStorage(outStorage);
}


}