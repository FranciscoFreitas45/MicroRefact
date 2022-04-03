package com.zis.Interface;
public interface DoPurchaseService {

   public String addPurchaseDetail(int bookId,int purchasedAmount,String operator,String position,String memo,Integer repoId,Integer stockAmount);
}