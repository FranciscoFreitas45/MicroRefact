package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WebHijackPreventionStrategyController {

 private WebHijackPreventionStrategy webhijackpreventionstrategy;


@GetMapping
("/protect")
public String protect(@RequestParam(name = "str") String str){
  return webhijackpreventionstrategy.protect(str);
}


}