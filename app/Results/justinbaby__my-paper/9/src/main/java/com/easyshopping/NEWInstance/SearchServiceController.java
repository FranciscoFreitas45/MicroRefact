package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SearchServiceController {

 private SearchService searchservice;


@GetMapping
("/search")
public Page<Product> search(@RequestParam(name = "keyword") String keyword,@RequestParam(name = "startPrice") BigDecimal startPrice,@RequestParam(name = "endPrice") BigDecimal endPrice,@RequestParam(name = "orderType") OrderType orderType,@RequestParam(name = "pageable") Pageable pageable){
  return searchservice.search(keyword,startPrice,endPrice,orderType,pageable);
}


}