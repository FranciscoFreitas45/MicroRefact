package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountBucketCollaboratorController {

@Autowired
 private AccountBucketCollaboratorService accountbucketcollaboratorservice;


@GetMapping
("/BucketCollaborator/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long idXXQN){
return accountbucketcollaboratorservice.getAccount(idXXQN);
}


@PutMapping
("/BucketCollaborator/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long idXXQN,@RequestBody Account account){
accountbucketcollaboratorservice.setAccount(idXXQN,account);
}


}