package com.zis.Interface;
public interface DoPurchaseService {

   public Page<TempImportTask> findAllTempImportTask(Pageable page);
}