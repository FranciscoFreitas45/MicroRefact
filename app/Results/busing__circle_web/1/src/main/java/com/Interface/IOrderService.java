package com.Interface;
public interface IOrderService {

   public List<Map<String,Object>> queryOrderList(Page page,int userId);
   public Map<String,List<Map<String,Object>>> queryOrderDetailList(String orderId);
   public List<Map<String,Object>> queryMOrderDetailList(String orderId);
}