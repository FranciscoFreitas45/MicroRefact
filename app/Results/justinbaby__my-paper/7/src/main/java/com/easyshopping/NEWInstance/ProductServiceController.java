package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductServiceController {

 private ProductService productservice;


@GetMapping
("/findBySn")
public Product findBySn(@RequestParam(name = "sn") String sn){
  return productservice.findBySn(sn);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return productservice.find(Object);
}


@GetMapping
("/isPurchased")
public boolean isPurchased(@RequestParam(name = "member") Member member,@RequestParam(name = "product") Product product){
  return productservice.isPurchased(member,product);
}


@GetMapping
("/count")
public Long count(@RequestParam(name = "favoriteMember") Member favoriteMember,@RequestParam(name = "isMarketable") Boolean isMarketable,@RequestParam(name = "isList") Boolean isList,@RequestParam(name = "isTop") Boolean isTop,@RequestParam(name = "isGift") Boolean isGift,@RequestParam(name = "isOutOfStock") Boolean isOutOfStock,@RequestParam(name = "isStockAlert") Boolean isStockAlert){
  return productservice.count(favoriteMember,isMarketable,isList,isTop,isGift,isOutOfStock,isStockAlert);
}


@GetMapping
("/findPage")
public Page<Product> findPage(@RequestParam(name = "member") Member member,@RequestParam(name = "pageable") Pageable pageable){
  return productservice.findPage(member,pageable);
}


@GetMapping
("/search")
public List<Product> search(@RequestParam(name = "keyword") String keyword,@RequestParam(name = "isGift") Boolean isGift,@RequestParam(name = "count") Integer count){
  return productservice.search(keyword,isGift,count);
}


@GetMapping
("/findList")
public List<Product> findList(@RequestParam(name = "productCategory") ProductCategory productCategory,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate,@RequestParam(name = "first") Integer first,@RequestParam(name = "count") Integer count){
  return productservice.findList(productCategory,beginDate,endDate,first,count);
}


@GetMapping
("/findSalesList")
public List<Object[]> findSalesList(@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate,@RequestParam(name = "count") Integer count){
  return productservice.findSalesList(beginDate,endDate,count);
}


}