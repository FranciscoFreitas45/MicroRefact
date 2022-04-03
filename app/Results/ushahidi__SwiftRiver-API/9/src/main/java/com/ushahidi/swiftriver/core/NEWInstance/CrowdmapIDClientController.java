package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CrowdmapIDClientController {

 private CrowdmapIDClient crowdmapidclient;


@GetMapping
("/isRegistered")
public boolean isRegistered(@RequestParam(name = "email") String email){
  return crowdmapidclient.isRegistered(email);
}


@GetMapping
("/register")
public String register(@RequestParam(name = "email") String email,@RequestParam(name = "password") String password){
  return crowdmapidclient.register(email,password);
}


}