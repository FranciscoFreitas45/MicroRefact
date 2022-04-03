package com.zis.Interface;
public interface OrderService {

   public StorageIoDetail lackPart(Integer ioDetailId,Integer actualAmt,Integer operator);
   public StorageIoDetail lackAll(Integer ioDetailId,Integer operator);
   public void finishSend(Integer repoId,Integer batchId,Integer operator);
}