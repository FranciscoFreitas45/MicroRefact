package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductCategoryServiceController {

 private ProductCategoryService productcategoryservice;


@GetMapping
("/findRoots")
public List<ProductCategory> findRoots(@RequestParam(name = "count") Integer count,@RequestParam(name = "cacheRegion") String cacheRegion){
  return productcategoryservice.findRoots(count,cacheRegion);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return productcategoryservice.findAll(Object);
}


@GetMapping
("/findList")
public Object findList(@RequestParam(name = "Object") Object Object){
  return productcategoryservice.findList(Object);
}


@GetMapping
("/findTree")
public List<ProductCategory> findTree(){
  return productcategoryservice.findTree();
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return productcategoryservice.find(Object);
}


@GetMapping
("/findChildren")
public List<ProductCategory> findChildren(@RequestParam(name = "productCategory") ProductCategory productCategory,@RequestParam(name = "count") Integer count,@RequestParam(name = "cacheRegion") String cacheRegion){
  return productcategoryservice.findChildren(productCategory,count,cacheRegion);
}


}