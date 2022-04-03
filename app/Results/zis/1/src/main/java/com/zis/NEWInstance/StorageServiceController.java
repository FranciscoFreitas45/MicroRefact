package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StorageServiceController {

 private StorageService storageservice;


@GetMapping
("/findStorageRepoInfoByCompanyId")
public List<StorageRepoInfo> findStorageRepoInfoByCompanyId(@RequestParam(name = "companyId") Integer companyId){
  return storageservice.findStorageRepoInfoByCompanyId(companyId);
}


@GetMapping
("/findBySkuIdAndRepoId")
public StorageProduct findBySkuIdAndRepoId(@RequestParam(name = "skuId") Integer skuId,@RequestParam(name = "repoId") Integer repoId){
  return storageservice.findBySkuIdAndRepoId(skuId,repoId);
}


@GetMapping
("/findStorageIoDetailByRepoIdAndBatchIdInAndIoDetailTypeAndDetailStatusIn")
public List<StorageIoDetail> findStorageIoDetailByRepoIdAndBatchIdInAndIoDetailTypeAndDetailStatusIn(@RequestParam(name = "repoId") Integer repoId,@RequestParam(name = "batchIds") List<Integer> batchIds,@RequestParam(name = "ioDetailType") String ioDetailType,@RequestParam(name = "DetailStatusList") List<String> DetailStatusList){
  return storageservice.findStorageIoDetailByRepoIdAndBatchIdInAndIoDetailTypeAndDetailStatusIn(repoId,batchIds,ioDetailType,DetailStatusList);
}


@PutMapping
("/directInStorage")
public void directInStorage(@RequestParam(name = "repoId") Integer repoId,@RequestParam(name = "skuId") Integer skuId,@RequestParam(name = "amount") Integer amount,@RequestParam(name = "posLabel") String posLabel,@RequestParam(name = "operator") Integer operator){
storageservice.directInStorage(repoId,skuId,amount,posLabel,operator);
}


@GetMapping
("/addInStorageDetail")
public StorageIoDetail addInStorageDetail(@RequestParam(name = "batchId") Integer batchId,@RequestParam(name = "skuId") Integer skuId,@RequestParam(name = "amount") Integer amount,@RequestParam(name = "posLabel") String posLabel,@RequestParam(name = "operator") Integer operator){
  return storageservice.addInStorageDetail(batchId,skuId,amount,posLabel,operator);
}


@PutMapping
("/cancelOrder")
public void cancelOrder(@RequestParam(name = "repoId") Integer repoId,@RequestParam(name = "outOrderId") Integer outOrderId){
storageservice.cancelOrder(repoId,outOrderId);
}


@GetMapping
("/createOrder")
public StorageOrder createOrder(@RequestParam(name = "request") CreateOrderDTO request){
  return storageservice.createOrder(request);
}


@GetMapping
("/arrangeOrder")
public int arrangeOrder(@RequestParam(name = "repoId") Integer repoId,@RequestParam(name = "outOrderIds") List<Integer> outOrderIds,@RequestParam(name = "operator") Integer operator){
  return storageservice.arrangeOrder(repoId,outOrderIds,operator);
}


@GetMapping
("/finishBatchSend")
public List<Integer> finishBatchSend(@RequestParam(name = "batchId") Integer batchId){
  return storageservice.finishBatchSend(batchId);
}


@GetMapping
("/lackAll")
public StorageLacknessOpDTO lackAll(@RequestParam(name = "ioDetailId") Integer ioDetailId,@RequestParam(name = "operator") Integer operator){
  return storageservice.lackAll(ioDetailId,operator);
}


@GetMapping
("/lackPart")
public StorageLacknessOpDTO lackPart(@RequestParam(name = "ioDetailId") Integer ioDetailId,@RequestParam(name = "operator") Integer operator,@RequestParam(name = "actualAmt") Integer actualAmt){
  return storageservice.lackPart(ioDetailId,operator,actualAmt);
}


}