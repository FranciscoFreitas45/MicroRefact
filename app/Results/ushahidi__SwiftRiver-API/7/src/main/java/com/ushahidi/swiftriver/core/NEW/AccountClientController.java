package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountClientController {

@Autowired
 private AccountClientService accountclientservice;


@GetMapping
("/Client/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long id159A){
return accountclientservice.getAccount(id159A);
}


@PutMapping
("/Client/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long id159A,@RequestBody Account account){
accountclientservice.setAccount(id159A,account);
}


}