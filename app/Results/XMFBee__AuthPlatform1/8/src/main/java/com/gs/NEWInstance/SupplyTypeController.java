package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SupplyTypeController {

 private SupplyType supplytype;

 private SupplyType supplytype;


@PutMapping
("/setCompanyId")
public void setCompanyId(@RequestParam(name = "companyId") String companyId){
supplytype.setCompanyId(companyId);
}


}