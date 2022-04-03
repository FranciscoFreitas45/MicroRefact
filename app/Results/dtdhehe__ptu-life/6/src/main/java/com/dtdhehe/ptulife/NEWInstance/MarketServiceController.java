package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MarketServiceController {

 private MarketService marketservice;


@GetMapping
("/queryGoodsById")
public Page<Market> queryGoodsById(@RequestParam(name = "id") String id,@RequestParam(name = "goodsName") String goodsName,@RequestParam(name = "pageable") Pageable pageable){
  return marketservice.queryGoodsById(id,goodsName,pageable);
}


@PutMapping
("/delGoodsById")
public void delGoodsById(@RequestParam(name = "id") String id){
marketservice.delGoodsById(id);
}


}