package com.easyshopping.Interface;
public interface SearchService {

   public Page<Product> search(String keyword,BigDecimal startPrice,BigDecimal endPrice,OrderType orderType,Pageable pageable);
}