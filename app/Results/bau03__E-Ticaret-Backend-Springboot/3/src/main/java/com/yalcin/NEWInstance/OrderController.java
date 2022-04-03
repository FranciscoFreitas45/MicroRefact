package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderController {

 private Order order;


@PutMapping
("/setAdress/{id}")
public void setAdress(@PathVariable(name = "id") Integer id,@RequestParam(name = "adress") Adress adress){
 orderrepository.setAdress(id,adress);
}


@PutMapping
("/setEnabled/{id}")
public void setEnabled(@PathVariable(name = "id") Integer id,@RequestParam(name = "enabled") boolean enabled){
 orderrepository.setEnabled(id,enabled);
}


@PutMapping
("/setTimestamp/{id}")
public void setTimestamp(@PathVariable(name = "id") Integer id,@RequestParam(name = "timestamp") Date timestamp){
 orderrepository.setTimestamp(id,timestamp);
}


@PutMapping
("/setProduct/{id}")
public void setProduct(@PathVariable(name = "id") Integer id,@RequestParam(name = "product") Product product){
 orderrepository.setProduct(id,product);
}


}