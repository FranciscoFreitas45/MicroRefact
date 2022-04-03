package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountFormController {

@Autowired
 private AccountFormService accountformservice;


@GetMapping
("/Form/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long idQGFJ){
return accountformservice.getAccount(idQGFJ);
}


@PutMapping
("/Form/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long idQGFJ,@RequestBody Account account){
accountformservice.setAccount(idQGFJ,account);
}


}