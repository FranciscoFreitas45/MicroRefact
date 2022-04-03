package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DepositDaoController {

 private DepositDao depositdao;


@GetMapping
("/persist")
public Object persist(@RequestParam(name = "Object") Object Object){
  return depositdao.persist(Object);
}


}