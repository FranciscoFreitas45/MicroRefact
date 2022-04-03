package com.easyshopping.Interface;
public interface OrderService {

   public Long waitingPaymentCount(Member member);
   public Long waitingShippingCount(Member member);
   public List<Order> findList(Member member,Integer count,List<Filter> filters,List<com.easyshopping.Order> orders);
}