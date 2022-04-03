package com.zis.Interface;
public interface StorageService {

   public List<StorageIoDetail> findStorageIoDetailByRepoIdAndBatchIdInAndIoDetailTypeAndDetailStatusIn(Integer repoId,List<Integer> batchIds,String ioDetailType,List<String> DetailStatusList);
}