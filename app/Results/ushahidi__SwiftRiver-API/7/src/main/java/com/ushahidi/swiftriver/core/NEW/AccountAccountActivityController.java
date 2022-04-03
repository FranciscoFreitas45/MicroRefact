package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountAccountActivityController {

@Autowired
 private AccountAccountActivityService accountaccountactivityservice;


@GetMapping
("/AccountActivity/{id}/Account/getActionOnObj")
public Account getActionOnObj(@PathVariable(name="id") long idD2H4){
return accountaccountactivityservice.getActionOnObj(idD2H4);
}


@PutMapping
("/AccountActivity/{id}/Account/setActionOnObj")
public void setActionOnObj(@PathVariable(name="id") long idD2H4,@RequestBody Account actionOnObj){
accountaccountactivityservice.setActionOnObj(idD2H4,actionOnObj);
}


}