package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DoPurchaseServiceController {

 private DoPurchaseService dopurchaseservice;


@GetMapping
("/findTempImportDetail")
public List<TempImportDetailView> findTempImportDetail(@RequestParam(name = "taskId") Integer taskId,@RequestParam(name = "tempImportDetailStatus") String tempImportDetailStatus){
  return dopurchaseservice.findTempImportDetail(taskId,tempImportDetailStatus);
}


@GetMapping
("/findInwarehouseDetailByInwarehouseIds")
public List<InwarehouseDetail> findInwarehouseDetailByInwarehouseIds(@RequestParam(name = "inwarehouseIds") Integer[] inwarehouseIds){
  return dopurchaseservice.findInwarehouseDetailByInwarehouseIds(inwarehouseIds);
}


@GetMapping
("/findPurchaseDetailByBatchId")
public List<PurchaseDetail> findPurchaseDetailByBatchId(@RequestParam(name = "batchId") Integer batchId){
  return dopurchaseservice.findPurchaseDetailByBatchId(batchId);
}


@GetMapping
("/findInwarehouseDetailByCriteria")
public Object findInwarehouseDetailByCriteria(@RequestParam(name = "Object") Object Object){
  return dopurchaseservice.findInwarehouseDetailByCriteria(Object);
}


@GetMapping
("/findPurchasePlanByBookId")
public PurchasePlan findPurchasePlanByBookId(@RequestParam(name = "bookId") int bookId,@RequestParam(name = "repoId") Integer repoId){
  return dopurchaseservice.findPurchasePlanByBookId(bookId,repoId);
}


@GetMapping
("/addBlackList")
public String addBlackList(@RequestParam(name = "bookId") Integer bookId,@RequestParam(name = "repoId") Integer repoId){
  return dopurchaseservice.addBlackList(bookId,repoId);
}


@GetMapping
("/findPurchasePlanByIsbn")
public List<PurchasePlan> findPurchasePlanByIsbn(@RequestParam(name = "isbn") String isbn,@RequestParam(name = "repoId") Integer repoId){
  return dopurchaseservice.findPurchasePlanByIsbn(isbn,repoId);
}


@GetMapping
("/calculateStillRequireAmount")
public Integer calculateStillRequireAmount(@RequestParam(name = "plan") PurchasePlan plan,@RequestParam(name = "stockAmount") Integer stockAmount){
  return dopurchaseservice.calculateStillRequireAmount(plan,stockAmount);
}


@GetMapping
("/addPurchaseDetail")
public String addPurchaseDetail(@RequestParam(name = "bookId") int bookId,@RequestParam(name = "purchasedAmount") int purchasedAmount,@RequestParam(name = "operator") String operator,@RequestParam(name = "position") String position,@RequestParam(name = "memo") String memo,@RequestParam(name = "repoId") Integer repoId,@RequestParam(name = "stockAmount") Integer stockAmount){
  return dopurchaseservice.addPurchaseDetail(bookId,purchasedAmount,operator,position,memo,repoId,stockAmount);
}


@PutMapping
("/updateTempImportDetail")
public void updateTempImportDetail(@RequestParam(name = "taskId") Integer taskId){
dopurchaseservice.updateTempImportDetail(taskId);
}


@GetMapping
("/findTempImportTaskByTaskId")
public TempImportTask findTempImportTaskByTaskId(@RequestParam(name = "taskId") Integer taskId){
  return dopurchaseservice.findTempImportTaskByTaskId(taskId);
}


@GetMapping
("/findTempImportDetailBySpecPage")
public Page<TempImportDetail> findTempImportDetailBySpecPage(@RequestParam(name = "spec") Specification<TempImportDetail> spec,@RequestParam(name = "page") Pageable page){
  return dopurchaseservice.findTempImportDetailBySpecPage(spec,page);
}


@PutMapping
("/addBookStock")
public void addBookStock(@RequestParam(name = "importList") List<StockDTO> importList,@RequestParam(name = "repoId") Integer repoId){
dopurchaseservice.addBookStock(importList,repoId);
}


@GetMapping
("/findAllTempImportTask")
public Page<TempImportTask> findAllTempImportTask(@RequestParam(name = "page") Pageable page){
  return dopurchaseservice.findAllTempImportTask(page);
}


@PutMapping
("/addTempImportTask")
public void addTempImportTask(@RequestParam(name = "list") List<TempImportDTO> list,@RequestParam(name = "bizType") TempImportTaskBizTypeEnum bizType,@RequestParam(name = "memo") String memo){
dopurchaseservice.addTempImportTask(list,bizType,memo);
}


}