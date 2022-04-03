package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccessoriesController {

 private Accessories accessories;


@PutMapping
("/setAccTypeId")
public void setAccTypeId(@RequestParam(name = "accTypeId") String accTypeId){
accessories.setAccTypeId(accTypeId);
}


@PutMapping
("/setSupplyId")
public void setSupplyId(@RequestParam(name = "supplyId") String supplyId){
accessories.setSupplyId(supplyId);
}


@PutMapping
("/setAccName")
public void setAccName(@RequestParam(name = "accName") String accName){
accessories.setAccName(accName);
}


}