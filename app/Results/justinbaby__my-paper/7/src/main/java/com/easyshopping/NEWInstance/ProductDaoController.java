package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductDaoController {

 private ProductDao productdao;


@GetMapping
("/lock")
public Object lock(@RequestParam(name = "Object") Object Object){
  return productdao.lock(Object);
}


@GetMapping
("/merge")
public Object merge(@RequestParam(name = "Object") Object Object){
  return productdao.merge(Object);
}


@GetMapping
("/flush")
public Object flush(@RequestParam(name = "Object") Object Object){
  return productdao.flush(Object);
}


@GetMapping
("/findList")
public List<Product> findList(@RequestParam(name = "goods") Goods goods,@RequestParam(name = "excludes") Set<Product> excludes){
  return productdao.findList(goods,excludes);
}


@GetMapping
("/clear")
public Object clear(@RequestParam(name = "Object") Object Object){
  return productdao.clear(Object);
}


@GetMapping
("/count")
public Long count(@RequestParam(name = "favoriteMember") Member favoriteMember,@RequestParam(name = "isMarketable") Boolean isMarketable,@RequestParam(name = "isList") Boolean isList,@RequestParam(name = "isTop") Boolean isTop,@RequestParam(name = "isGift") Boolean isGift,@RequestParam(name = "isOutOfStock") Boolean isOutOfStock,@RequestParam(name = "isStockAlert") Boolean isStockAlert){
  return productdao.count(favoriteMember,isMarketable,isList,isTop,isGift,isOutOfStock,isStockAlert);
}


}