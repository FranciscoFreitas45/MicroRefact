package com.zis.Interface;
public interface OrderService {

   public Order createOrder(CreateTradeOrderDTO orderDTO);
   public void payOrder(Integer shopId,String outOrderNumber,Double paymentAmount,Integer operator);
   public void arrangeOrderToRepo(Integer orderId,Integer operator,Integer repoId);
   public void applyRefund(Integer shopId,String outOrderNumber,Integer operator,Date applyTime,String refundMemo);
}