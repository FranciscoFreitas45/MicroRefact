package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class INewsStatusServiceController {

 private INewsStatusService inewsstatusservice;


@GetMapping
("/listTopNInsertRecordsByTableName")
public List<NewsStatusEntity> listTopNInsertRecordsByTableName(@RequestParam(name = "tableName") String tableName,@RequestParam(name = "topN") int topN){
  return inewsstatusservice.listTopNInsertRecordsByTableName(tableName,topN);
}


@GetMapping
("/deleteEsStatusList")
public Boolean deleteEsStatusList(@RequestParam(name = "esStatusList") List<NewsStatusEntity> esStatusList){
  return inewsstatusservice.deleteEsStatusList(esStatusList);
}


@GetMapping
("/listTopNDeletedRecordsByTableName")
public List<NewsStatusEntity> listTopNDeletedRecordsByTableName(@RequestParam(name = "tableName") String tableName,@RequestParam(name = "topN") int topN){
  return inewsstatusservice.listTopNDeletedRecordsByTableName(tableName,topN);
}


@GetMapping
("/listTopNUpdateRecordsByTableName")
public List<NewsStatusEntity> listTopNUpdateRecordsByTableName(@RequestParam(name = "tableName") String tableName,@RequestParam(name = "topN") int topN){
  return inewsstatusservice.listTopNUpdateRecordsByTableName(tableName,topN);
}


}