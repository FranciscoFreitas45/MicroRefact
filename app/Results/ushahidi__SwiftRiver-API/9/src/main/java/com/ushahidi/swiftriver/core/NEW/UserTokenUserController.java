package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.UserToken;
@RestController
@CrossOrigin
public class UserTokenUserController {

@Autowired
 private UserTokenUserService usertokenuserservice;


@GetMapping
("/User/{id}/UserToken/getTokens")
public Set<UserToken> getTokens(@PathVariable(name="id") long id){
return usertokenuserservice.getTokens(id);
}


@PutMapping
("/User/{id}/UserToken/setTokens")
public void setTokens(@PathVariable(name="id") long id,@RequestBody Set<UserToken> tokens){
usertokenuserservice.setTokens(id,tokens);
}


}