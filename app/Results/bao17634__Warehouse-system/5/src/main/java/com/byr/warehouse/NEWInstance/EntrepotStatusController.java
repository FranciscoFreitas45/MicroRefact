package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EntrepotStatusController {

 private EntrepotStatusRepository entrepotstatusrepository;


@PutMapping
("/setTotalSize/{id}")
public void setTotalSize(@PathVariable(name = "id") Long id,@RequestParam(name = "totalSize") int totalSize){
 entrepotstatusrepository.setTotalSize(id,totalSize);
}


@PutMapping
("/setEnterCode/{id}")
public void setEnterCode(@PathVariable(name = "id") Long id,@RequestParam(name = "enterCode") String enterCode){
 entrepotstatusrepository.setEnterCode(id,enterCode);
}


@PutMapping
("/setMaterialCode/{id}")
public void setMaterialCode(@PathVariable(name = "id") Long id,@RequestParam(name = "materialCode") String materialCode){
 entrepotstatusrepository.setMaterialCode(id,materialCode);
}


@PutMapping
("/setEntranceDate/{id}")
public void setEntranceDate(@PathVariable(name = "id") Long id,@RequestParam(name = "entranceDate") Date entranceDate){
 entrepotstatusrepository.setEntranceDate(id,entranceDate);
}


@PutMapping
("/setUpdateDate/{id}")
public void setUpdateDate(@PathVariable(name = "id") Long id,@RequestParam(name = "updateDate") Date updateDate){
 entrepotstatusrepository.setUpdateDate(id,updateDate);
}


@PutMapping
("/setProduceDate/{id}")
public void setProduceDate(@PathVariable(name = "id") Long id,@RequestParam(name = "produceDate") String produceDate){
 entrepotstatusrepository.setProduceDate(id,produceDate);
}


@PutMapping
("/setMaterialSpec/{id}")
public void setMaterialSpec(@PathVariable(name = "id") Long id,@RequestParam(name = "materialSpec") String materialSpec){
 entrepotstatusrepository.setMaterialSpec(id,materialSpec);
}


@PutMapping
("/setProductName/{id}")
public void setProductName(@PathVariable(name = "id") Long id,@RequestParam(name = "productName") String productName){
 entrepotstatusrepository.setProductName(id,productName);
}


@PutMapping
("/setPosition/{id}")
public void setPosition(@PathVariable(name = "id") Long id,@RequestParam(name = "position") String position){
 entrepotstatusrepository.setPosition(id,position);
}


}