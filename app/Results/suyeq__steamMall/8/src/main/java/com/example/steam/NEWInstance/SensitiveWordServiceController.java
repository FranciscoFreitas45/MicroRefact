package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SensitiveWordServiceController {

 private SensitiveWordService sensitivewordservice;


@PutMapping
("/addSensitiveWord")
public void addSensitiveWord(@RequestParam(name = "word") String word){
sensitivewordservice.addSensitiveWord(word);
}


@GetMapping
("/sensitiveVo")
public String sensitiveVo(){
  return sensitivewordservice.sensitiveVo();
}


}