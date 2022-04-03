package com.easyshopping.Interface;
public interface ProductService {

   public List<Object[]> findSalesList(Date beginDate,Date endDate,Integer count);
}