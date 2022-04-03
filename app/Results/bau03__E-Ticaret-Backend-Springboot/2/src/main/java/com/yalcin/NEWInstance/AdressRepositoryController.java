package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdressRepositoryController {

 private AdressRepository adressrepository;


@GetMapping
("/findAllById")
public Adress findAllById(@RequestParam(name = "id") Integer id){
  return adressrepository.findAllById(id);
}


}