package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RSAServiceController {

 private RSAService rsaservice;


@GetMapping
("/generateKey")
public RSAPublicKey generateKey(@RequestParam(name = "request") HttpServletRequest request){
  return rsaservice.generateKey(request);
}


}