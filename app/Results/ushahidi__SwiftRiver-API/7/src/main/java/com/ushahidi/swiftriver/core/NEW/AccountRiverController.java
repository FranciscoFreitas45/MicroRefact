package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountRiverController {

@Autowired
 private AccountRiverService accountriverservice;


@GetMapping
("/River/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long idXN57){
return accountriverservice.getAccount(idXN57);
}


@PutMapping
("/River/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long idXN57,@RequestBody Account account){
accountriverservice.setAccount(idXN57,account);
}


}