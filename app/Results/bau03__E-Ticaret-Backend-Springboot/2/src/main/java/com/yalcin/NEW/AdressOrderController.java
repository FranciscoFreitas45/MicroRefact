package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.Adress;
@RestController
@CrossOrigin
public class AdressOrderController {

@Autowired
 private AdressOrderService adressorderservice;


@PutMapping
("/Order/{id}/Adress/setAdress")
public void setAdress(@PathVariable(name="id") Integer id,@RequestBody Adress adress){
adressorderservice.setAdress(id,adress);
}


@GetMapping
("/Order/{id}/Adress/getAdress")
public Adress getAdress(@PathVariable(name="id") Integer id){
return adressorderservice.getAdress(id);
}


}