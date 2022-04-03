package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdressServiceController {

 private AdressService adressservice;


@GetMapping
("/getAdress")
public List<Adress> getAdress(@RequestParam(name = "userId") String userId){
  return adressservice.getAdress(userId);
}


@PutMapping
("/adressSave")
public void adressSave(@RequestParam(name = "adressFrom") AdressFrom adressFrom){
adressservice.adressSave(adressFrom);
}


}