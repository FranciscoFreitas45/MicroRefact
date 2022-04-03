package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountBucketDropCommentController {

@Autowired
 private AccountBucketDropCommentService accountbucketdropcommentservice;


@GetMapping
("/BucketDropComment/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long idLW0Z){
return accountbucketdropcommentservice.getAccount(idLW0Z);
}


@PutMapping
("/BucketDropComment/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long idLW0Z,@RequestBody Account account){
accountbucketdropcommentservice.setAccount(idLW0Z,account);
}


}