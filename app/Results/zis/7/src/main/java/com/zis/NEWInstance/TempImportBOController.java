package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TempImportBOController {

 private TempImportBO tempimportbo;


@PutMapping
("/addTempImportTask")
public void addTempImportTask(@RequestParam(name = "list") List<TempImportDTO> list,@RequestParam(name = "bizType") TempImportTaskBizTypeEnum bizType,@RequestParam(name = "memo") String memo){
tempimportbo.addTempImportTask(list,bizType,memo);
}


@PutMapping
("/updateTempImportDetail")
public void updateTempImportDetail(@RequestParam(name = "taskId") Integer taskId){
tempimportbo.updateTempImportDetail(taskId);
}


@GetMapping
("/updateAssociateTempImportDetailWithBookInfo")
public String updateAssociateTempImportDetailWithBookInfo(@RequestParam(name = "tempImportDetailId") Integer tempImportDetailId,@RequestParam(name = "bookId") Integer bookId){
  return tempimportbo.updateAssociateTempImportDetailWithBookInfo(tempImportDetailId,bookId);
}


@GetMapping
("/updateAssociatePurchaseTempImportWithBookInfo")
public String updateAssociatePurchaseTempImportWithBookInfo(@RequestParam(name = "detail") TempImportDetail detail,@RequestParam(name = "book") Bookinfo book){
  return tempimportbo.updateAssociatePurchaseTempImportWithBookInfo(detail,book);
}


@GetMapping
("/findAllTempImportTask")
public Page<TempImportTask> findAllTempImportTask(@RequestParam(name = "page") Pageable page){
  return tempimportbo.findAllTempImportTask(page);
}


@GetMapping
("/findTempImportTaskByTaskId")
public TempImportTask findTempImportTaskByTaskId(@RequestParam(name = "taskId") Integer taskId){
  return tempimportbo.findTempImportTaskByTaskId(taskId);
}


@GetMapping
("/findTempImportDetail")
public List<TempImportDetailView> findTempImportDetail(@RequestParam(name = "taskId") Integer taskId,@RequestParam(name = "tempImportDetailStatus") String tempImportDetailStatus){
  return tempimportbo.findTempImportDetail(taskId,tempImportDetailStatus);
}


@PutMapping
("/updateTempImportTaskStatus")
public void updateTempImportTaskStatus(@RequestParam(name = "taskId") Integer taskId,@RequestParam(name = "tempImportTaskStatus") Integer tempImportTaskStatus){
tempimportbo.updateTempImportTaskStatus(taskId,tempImportTaskStatus);
}


}