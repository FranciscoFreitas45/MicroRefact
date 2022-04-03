package com.zis.Interface;
public interface DoPurchaseService {

   public List<TempImportDetailView> findTempImportDetail(Integer taskId,String tempImportDetailStatus);
   public PurchasePlan findPurchasePlanByBookId(int bookId,Integer repoId);
}