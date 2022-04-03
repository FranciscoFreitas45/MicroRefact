package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MaterialListController {

 private MaterialList materiallist;

 private MaterialList materiallist;


@PutMapping
("/setAccId")
public void setAccId(@RequestParam(name = "accId") String accId){
materiallist.setAccId(accId);
}


@PutMapping
("/setMaterialCount")
public void setMaterialCount(@RequestParam(name = "materialCount") Integer materialCount){
materiallist.setMaterialCount(materialCount);
}


}