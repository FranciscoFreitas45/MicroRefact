package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StorageProductDaoController {

 private StorageProductDao storageproductdao;


@GetMapping
("/findBySkuIdAndRepoId")
public StorageProduct findBySkuIdAndRepoId(@RequestParam(name = "skuId") Integer skuId,@RequestParam(name = "repoId") Integer repoId){
  return storageproductdao.findBySkuIdAndRepoId(skuId,repoId);
}


}