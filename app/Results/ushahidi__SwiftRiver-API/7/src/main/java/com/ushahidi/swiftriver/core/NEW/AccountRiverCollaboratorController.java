package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountRiverCollaboratorController {

@Autowired
 private AccountRiverCollaboratorService accountrivercollaboratorservice;


@GetMapping
("/RiverCollaborator/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long idWINU){
return accountrivercollaboratorservice.getAccount(idWINU);
}


@PutMapping
("/RiverCollaborator/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long idWINU,@RequestBody Account account){
accountrivercollaboratorservice.setAccount(idWINU,account);
}


}