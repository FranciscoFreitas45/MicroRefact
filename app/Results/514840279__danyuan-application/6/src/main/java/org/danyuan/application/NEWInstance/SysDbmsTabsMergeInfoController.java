package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsTabsMergeInfoController {

 private SysDbmsTabsMergeInfoDao sysdbmstabsmergeinfodao;


@PutMapping
("/setTableUuid1/{id}")
public void setTableUuid1(@PathVariable(name = "id") String id,@RequestParam(name = "tableUuid1") String tableUuid1){
 sysdbmstabsmergeinfodao.setTableUuid1(id,tableUuid1);
}


@PutMapping
("/setColsName1/{id}")
public void setColsName1(@PathVariable(name = "id") String id,@RequestParam(name = "colsName1") String colsName1){
 sysdbmstabsmergeinfodao.setColsName1(id,colsName1);
}


@PutMapping
("/setColsDesc1/{id}")
public void setColsDesc1(@PathVariable(name = "id") String id,@RequestParam(name = "colsDesc1") String colsDesc1){
 sysdbmstabsmergeinfodao.setColsDesc1(id,colsDesc1);
}


@PutMapping
("/setColsUuid1/{id}")
public void setColsUuid1(@PathVariable(name = "id") String id,@RequestParam(name = "colsUuid1") String colsUuid1){
 sysdbmstabsmergeinfodao.setColsUuid1(id,colsUuid1);
}


@PutMapping
("/setTableUuid2/{id}")
public void setTableUuid2(@PathVariable(name = "id") String id,@RequestParam(name = "tableUuid2") String tableUuid2){
 sysdbmstabsmergeinfodao.setTableUuid2(id,tableUuid2);
}


@PutMapping
("/setColsName2/{id}")
public void setColsName2(@PathVariable(name = "id") String id,@RequestParam(name = "colsName2") String colsName2){
 sysdbmstabsmergeinfodao.setColsName2(id,colsName2);
}


@PutMapping
("/setColsDesc2/{id}")
public void setColsDesc2(@PathVariable(name = "id") String id,@RequestParam(name = "colsDesc2") String colsDesc2){
 sysdbmstabsmergeinfodao.setColsDesc2(id,colsDesc2);
}


@PutMapping
("/setColsUuid2/{id}")
public void setColsUuid2(@PathVariable(name = "id") String id,@RequestParam(name = "colsUuid2") String colsUuid2){
 sysdbmstabsmergeinfodao.setColsUuid2(id,colsUuid2);
}


}