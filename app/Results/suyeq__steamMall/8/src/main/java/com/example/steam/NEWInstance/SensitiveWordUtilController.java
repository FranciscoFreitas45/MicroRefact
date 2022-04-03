package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SensitiveWordUtilController {

 private SensitiveWordUtil sensitivewordutil;


@GetMapping
("/replaceSensitiveWord")
public String replaceSensitiveWord(@RequestParam(name = "text") String text){
  return sensitivewordutil.replaceSensitiveWord(text);
}


}