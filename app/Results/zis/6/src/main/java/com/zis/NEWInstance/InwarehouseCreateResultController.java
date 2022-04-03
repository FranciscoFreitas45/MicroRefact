package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InwarehouseCreateResultController {

 private InwarehouseCreateResult inwarehousecreateresult;

 private InwarehouseCreateResult inwarehousecreateresult;


@PutMapping
("/setInwarehouseId")
public void setInwarehouseId(@RequestParam(name = "inwarehouseId") Integer inwarehouseId){
inwarehousecreateresult.setInwarehouseId(inwarehouseId);
}


@PutMapping
("/setCurrentPosition")
public void setCurrentPosition(@RequestParam(name = "currentPosition") String currentPosition){
inwarehousecreateresult.setCurrentPosition(currentPosition);
}


}