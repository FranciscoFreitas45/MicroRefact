package com.zis.Interface;
public interface OrderOuterDao {

   public List<OrderOuter> findByOrderGroupNumber(String orderGroupNumber);
}