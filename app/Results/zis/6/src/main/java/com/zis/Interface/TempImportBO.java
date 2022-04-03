package com.zis.Interface;
public interface TempImportBO {

   public void addTempImportTask(List<TempImportDTO> list,TempImportTaskBizTypeEnum bizType,String memo);
   public void updateTempImportDetail(Integer taskId);
   public String updateAssociateTempImportDetailWithBookInfo(Integer tempImportDetailId,Integer bookId);
   public String updateAssociatePurchaseTempImportWithBookInfo(TempImportDetail detail,Bookinfo book);
   public Page<TempImportTask> findAllTempImportTask(Pageable page);
   public TempImportTask findTempImportTaskByTaskId(Integer taskId);
   public List<TempImportDetailView> findTempImportDetail(Integer taskId,String tempImportDetailStatus);
   public void updateTempImportTaskStatus(Integer taskId,Integer tempImportTaskStatus);
}