package com.easyshopping.Interface;
public interface OrderService {

   public BigDecimal getSalesAmount(Date beginDate,Date endDate);
   public Integer getSalesVolume(Date beginDate,Date endDate);
}