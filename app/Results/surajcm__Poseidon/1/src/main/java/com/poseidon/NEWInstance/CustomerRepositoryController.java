package com.poseidon.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CustomerRepositoryController {

 private CustomerRepository customerrepository;


@GetMapping
("/findByName")
public List<Customer> findByName(@RequestParam(name = "name") String name){
  return customerrepository.findByName(name);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return customerrepository.findById(Object);
}


}