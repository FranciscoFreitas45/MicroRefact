package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountBucketCommentController {

@Autowired
 private AccountBucketCommentService accountbucketcommentservice;


@GetMapping
("/BucketComment/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long idVO82){
return accountbucketcommentservice.getAccount(idVO82);
}


@PutMapping
("/BucketComment/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long idVO82,@RequestBody Account account){
accountbucketcommentservice.setAccount(idVO82,account);
}


}