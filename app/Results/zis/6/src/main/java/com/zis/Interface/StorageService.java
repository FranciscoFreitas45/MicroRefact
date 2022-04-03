package com.zis.Interface;
public interface StorageService {

   public void directInStorage(Integer repoId,Integer skuId,Integer amount,String posLabel,Integer operator);
   public StorageIoDetail addInStorageDetail(Integer batchId,Integer skuId,Integer amount,String posLabel,Integer operator);
}