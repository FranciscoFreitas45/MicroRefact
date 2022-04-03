package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SnDaoController {

 private SnDao sndao;


@GetMapping
("/generate")
public String generate(@RequestParam(name = "type") Type type){
  return sndao.generate(type);
}


}