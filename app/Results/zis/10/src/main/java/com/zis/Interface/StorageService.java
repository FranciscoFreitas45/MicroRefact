package com.zis.Interface;
public interface StorageService {

   public List<StorageRepoInfo> findStorageRepoInfoByCompanyId(Integer companyId);
   public StorageProduct findBySkuIdAndRepoId(Integer skuId,Integer repoId);
}