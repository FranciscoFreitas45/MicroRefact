package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MaterialListServiceController {

 private MaterialListService materiallistservice;


@PutMapping
("/insertList")
public void insertList(@RequestParam(name = "materialLists") List<MaterialList> materialLists){
materiallistservice.insertList(materialLists);
}


}