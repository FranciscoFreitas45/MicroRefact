package com.easyshopping.Interface;
public interface MemberService {

   public List<Object[]> findPurchaseList(Date beginDate,Date endDate,Integer count);
}