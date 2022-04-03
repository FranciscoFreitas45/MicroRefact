package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountActivityController {

@Autowired
 private AccountActivityService accountactivityservice;


@GetMapping
("/Activity/{id}/Account/getActionTo")
public Account getActionTo(@PathVariable(name="id") long idTMNI){
return accountactivityservice.getActionTo(idTMNI);
}


@PutMapping
("/Activity/{id}/Account/setActionTo")
public void setActionTo(@PathVariable(name="id") long idTMNI,@RequestBody Account actionTo){
accountactivityservice.setActionTo(idTMNI,actionTo);
}


@GetMapping
("/Activity/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long idTMNI){
return accountactivityservice.getAccount(idTMNI);
}


@PutMapping
("/Activity/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long idTMNI,@RequestBody Account account){
accountactivityservice.setAccount(idTMNI,account);
}


}