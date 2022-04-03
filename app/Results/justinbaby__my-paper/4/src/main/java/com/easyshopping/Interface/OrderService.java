package com.easyshopping.Interface;
public interface OrderService {

   public Long waitingPaymentCount(Member member);
   public Long waitingShippingCount(Member member);
}