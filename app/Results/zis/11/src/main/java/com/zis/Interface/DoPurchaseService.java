package com.zis.Interface;
public interface DoPurchaseService {

   public void addTempImportTask(List<TempImportDTO> list,TempImportTaskBizTypeEnum bizType,String memo);
}