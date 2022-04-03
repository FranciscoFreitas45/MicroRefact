package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Account;
@RestController
@CrossOrigin
public class AccountRiverDropCommentController {

@Autowired
 private AccountRiverDropCommentService accountriverdropcommentservice;


@GetMapping
("/RiverDropComment/{id}/Account/getAccount")
public Account getAccount(@PathVariable(name="id") long idG79G){
return accountriverdropcommentservice.getAccount(idG79G);
}


@PutMapping
("/RiverDropComment/{id}/Account/setAccount")
public void setAccount(@PathVariable(name="id") long idG79G,@RequestBody Account account){
accountriverdropcommentservice.setAccount(idG79G,account);
}


}