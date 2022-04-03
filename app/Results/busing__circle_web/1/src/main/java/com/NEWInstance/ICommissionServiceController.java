package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICommissionServiceController {

 private ICommissionService icommissionservice;


@GetMapping
("/increaseUserCommission")
public boolean increaseUserCommission(@RequestParam(name = "commissionHistory") CommissionHistory commissionHistory){
  return icommissionservice.increaseUserCommission(commissionHistory);
}


}