package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BannedAccessTokensController {

 private BannedAccessTokens bannedaccesstokens;


@PutMapping
("/addUser")
public void addUser(@RequestParam(name = "username") String username){
bannedaccesstokens.addUser(username);
}


}