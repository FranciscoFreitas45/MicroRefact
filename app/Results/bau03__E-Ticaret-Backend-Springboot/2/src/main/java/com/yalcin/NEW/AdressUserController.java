package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.Adress;
@RestController
@CrossOrigin
public class AdressUserController {

@Autowired
 private AdressUserService adressuserservice;


@PutMapping
("/User/{id}/Adress/setAdress")
public void setAdress(@PathVariable(name="id") Integer id,@RequestBody Set<Adress> adress){
adressuserservice.setAdress(id,adress);
}


@GetMapping
("/User/{id}/Adress/getAdress")
public Set<Adress> getAdress(@PathVariable(name="id") Integer id){
return adressuserservice.getAdress(id);
}


}