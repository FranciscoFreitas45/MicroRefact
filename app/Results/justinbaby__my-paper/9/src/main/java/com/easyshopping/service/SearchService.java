package com.easyshopping.service;
 import java.math.BigDecimal;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Article;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.Product.OrderType;
public interface SearchService {


public Page<Product> search(String keyword,BigDecimal startPrice,BigDecimal endPrice,OrderType orderType,Pageable pageable)
;

public void index(Product product)
;

public void purge(Product product)
;

}