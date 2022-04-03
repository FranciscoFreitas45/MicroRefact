package com.weflors.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientEntityController {

 private ClientEntity cliententity;

 private ClientEntity cliententity;


@PutMapping
("/setAmountPurchased")
public void setAmountPurchased(@RequestParam(name = "amountPurchased") BigDecimal amountPurchased){
cliententity.setAmountPurchased(amountPurchased);
}


}