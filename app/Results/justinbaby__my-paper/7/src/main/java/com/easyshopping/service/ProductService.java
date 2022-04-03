package com.easyshopping.service;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Attribute;
import com.easyshopping.entity.Brand;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.Product.OrderType;
import com.easyshopping.entity.ProductCategory;
import com.easyshopping.entity.Promotion;
import com.easyshopping.entity.Tag;
public interface ProductService extends BaseService<Product, Long>{


public List<Product> search(String keyword,Boolean isGift,Integer count)
;

public long viewHits(Long id)
;

public Product findBySn(String sn)
;

public List<Product> findList(ProductCategory productCategory,Date beginDate,Date endDate,Integer first,Integer count)
;

public boolean snExists(String sn)
;

public Long count(Member favoriteMember,Boolean isMarketable,Boolean isList,Boolean isTop,Boolean isGift,Boolean isOutOfStock,Boolean isStockAlert)
;

public List<Object[]> findSalesList(Date beginDate,Date endDate,Integer count)
;

public Page<Product> findPage(Member member,Pageable pageable)
;

public boolean snUnique(String previousSn,String currentSn)
;

public boolean isPurchased(Member member,Product product)
;

}