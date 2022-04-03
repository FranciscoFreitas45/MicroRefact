package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientController {

 private JpaClientDao jpaclientdao;


@PutMapping
("/setAccount/{id}")
public void setAccount(@PathVariable(name = "id") long id,@RequestParam(name = "account") Account account){
 jpaclientdao.setAccount(id,account);
}


@PutMapping
("/setRoles/{id}")
public void setRoles(@PathVariable(name = "id") long id,@RequestParam(name = "roles") Set<Role> roles){
 jpaclientdao.setRoles(id,roles);
}


@PutMapping
("/setActive/{id}")
public void setActive(@PathVariable(name = "id") long id,@RequestParam(name = "active") Boolean active){
 jpaclientdao.setActive(id,active);
}


@PutMapping
("/setClientSecret/{id}")
public void setClientSecret(@PathVariable(name = "id") long id,@RequestParam(name = "clientSecret") String clientSecret){
 jpaclientdao.setClientSecret(id,clientSecret);
}


@PutMapping
("/setClientId/{id}")
public void setClientId(@PathVariable(name = "id") long id,@RequestParam(name = "clientId") String clientId){
 jpaclientdao.setClientId(id,clientId);
}


}