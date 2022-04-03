package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SupplyController {

 private Supply supply;


@PutMapping
("/setCompanyId")
public void setCompanyId(@RequestParam(name = "companyId") String companyId){
supply.setCompanyId(companyId);
}


@PutMapping
("/setSupplyName")
public void setSupplyName(@RequestParam(name = "supplyName") String supplyName){
supply.setSupplyName(supplyName);
}


@PutMapping
("/setSupplyTypeId")
public void setSupplyTypeId(@RequestParam(name = "supplyTypeId") String supplyTypeId){
supply.setSupplyTypeId(supplyTypeId);
}


}