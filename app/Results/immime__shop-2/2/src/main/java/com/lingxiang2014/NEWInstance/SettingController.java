package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SettingController {

 private Setting setting;

 private Setting setting;


@GetMapping
("/setScale")
public BigDecimal setScale(@RequestParam(name = "amount") BigDecimal amount){
  return setting.setScale(amount);
}


}