package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserTokenController {

 private JpaUserTokenDao jpausertokendao;


@PutMapping
("/setExpires/{id}")
public void setExpires(@PathVariable(name = "id") long id,@RequestParam(name = "expires") Date expires){
 jpausertokendao.setExpires(id,expires);
}


@PutMapping
("/setUser/{id}")
public void setUser(@PathVariable(name = "id") long id,@RequestParam(name = "user") User user){
 jpausertokendao.setUser(id,user);
}


@PutMapping
("/setCreated/{id}")
public void setCreated(@PathVariable(name = "id") long id,@RequestParam(name = "created") Date created){
 jpausertokendao.setCreated(id,created);
}


}