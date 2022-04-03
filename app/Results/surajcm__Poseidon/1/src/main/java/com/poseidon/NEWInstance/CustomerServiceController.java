package com.poseidon.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CustomerServiceController {

 private CustomerService customerservice;


@GetMapping
("/saveCustomer")
public CustomerVO saveCustomer(@RequestParam(name = "currentCustomerVO") CustomerVO currentCustomerVO){
  return customerservice.saveCustomer(currentCustomerVO);
}


@GetMapping
("/getCustomerFromId")
public Optional<CustomerVO> getCustomerFromId(@RequestParam(name = "id") Long id){
  return customerservice.getCustomerFromId(id);
}


}