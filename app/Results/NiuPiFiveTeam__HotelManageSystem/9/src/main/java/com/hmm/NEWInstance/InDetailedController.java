package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InDetailedController {

 private InDetailedRepository indetailedrepository;


@PutMapping
("/setPrice/{id}")
public void setPrice(@PathVariable(name = "id") Long id,@RequestParam(name = "price") float price){
 indetailedrepository.setPrice(id,price);
}


}