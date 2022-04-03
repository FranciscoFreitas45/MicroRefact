package com.easyshopping.Interface;
public interface ProductDao {

   public List<Product> findList(Goods goods,Set<Product> excludes);
   public Object clear(Object Object);
   public Object flush(Object Object);
   public Long count(Member favoriteMember,Boolean isMarketable,Boolean isList,Boolean isTop,Boolean isGift,Boolean isOutOfStock,Boolean isStockAlert);
}