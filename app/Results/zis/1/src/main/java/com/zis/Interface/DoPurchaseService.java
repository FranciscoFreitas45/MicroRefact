package com.zis.Interface;
public interface DoPurchaseService {

   public List<PurchaseDetail> findPurchaseDetailByBatchId(Integer batchId);
}