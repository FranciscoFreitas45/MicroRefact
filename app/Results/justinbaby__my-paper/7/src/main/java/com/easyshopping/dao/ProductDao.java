package com.easyshopping.dao;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Attribute;
import com.easyshopping.entity.Brand;
import com.easyshopping.entity.Goods;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.Product.OrderType;
import com.easyshopping.entity.ProductCategory;
import com.easyshopping.entity.Promotion;
import com.easyshopping.entity.Tag;
public interface ProductDao extends BaseDao<Product, Long>{


public List<Product> search(String keyword,Boolean isGift,Integer count)
;

public Product findBySn(String sn)
;

public List<Product> findList(Goods goods,Set<Product> excludes)
;

public boolean snExists(String sn)
;

public Long count(Member favoriteMember,Boolean isMarketable,Boolean isList,Boolean isTop,Boolean isGift,Boolean isOutOfStock,Boolean isStockAlert)
;

public List<Object[]> findSalesList(Date beginDate,Date endDate,Integer count)
;

public Page<Product> findPage(Member member,Pageable pageable)
;

public boolean isPurchased(Member member,Product product)
;

}