package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FollowerDTOController {

 private FollowerDTO followerdto;

 private FollowerDTO followerdto;


@PutMapping
("/setEmail")
public void setEmail(@RequestParam(name = "email") String email){
followerdto.setEmail(email);
}


}