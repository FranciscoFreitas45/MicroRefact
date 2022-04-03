package com.zis.Interface;
public interface DoPurchaseService {

   public List<TempImportDetailView> findTempImportDetail(Integer taskId,String tempImportDetailStatus);
}