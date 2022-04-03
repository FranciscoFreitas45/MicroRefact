package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IDataIndexController {

 private IDataIndex idataindex;


@GetMapping
("/bulkUpdate")
public boolean bulkUpdate(@RequestParam(name = "indexName") String indexName,@RequestParam(name = "indexType") String indexType,@RequestParam(name = "entityList") List<T> entityList){
  return idataindex.bulkUpdate(indexName,indexType,entityList);
}


@GetMapping
("/bulkDelete")
public boolean bulkDelete(@RequestParam(name = "indexName") String indexName,@RequestParam(name = "indexType") String indexType,@RequestParam(name = "primaryKeyList") List<String> primaryKeyList){
  return idataindex.bulkDelete(indexName,indexType,primaryKeyList);
}


@GetMapping
("/delete")
public boolean delete(@RequestParam(name = "indexName") String indexName,@RequestParam(name = "indexType") String indexType,@RequestParam(name = "primaryKey") String primaryKey){
  return idataindex.delete(indexName,indexType,primaryKey);
}


}