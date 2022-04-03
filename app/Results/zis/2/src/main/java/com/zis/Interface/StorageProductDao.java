package com.zis.Interface;
public interface StorageProductDao {

   public StorageProduct findBySkuIdAndRepoId(Integer skuId,Integer repoId);
}