package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountBucketController {

@Autowired
 private AccountBucketService accountbucketservice;


@GetMapping
("/Bucket/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long id0O8K){
return accountbucketservice.getAccount(id0O8K);
}


@PutMapping
("/Bucket/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long id0O8K,@RequestBody Account account){
accountbucketservice.setAccount(id0O8K,account);
}


}