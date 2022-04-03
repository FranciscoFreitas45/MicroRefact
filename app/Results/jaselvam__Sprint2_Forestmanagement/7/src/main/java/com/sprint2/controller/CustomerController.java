package com.sprint2.controller;
 import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Customer;
import com.sprint2.service.CustomerService;
@RequestMapping("/customer")
@Controller
public class CustomerController implements ICustomerController{

 private Logger logger;

@Autowired
 private  CustomerService customerService;


@PostMapping("/")
@ResponseBody
public Customer serviceaddCustomer(Customer customer){
    logger.info("customer service add method was intalized");
    return customerService.serviceaddCustomer(customer);
}


@GetMapping(value = { "/" })
@ResponseBody
public List<Customer> servicegetAllCustomer(){
    logger.info("customer service was instalized");
    List<Customer> customer = customerService.servicegetAllCustomer();
    return customer;
}


@DeleteMapping("/{customerId}")
@ResponseBody
public String serviceremoveCustomer(int customerId){
    logger.info("customer service was instalized for remove method");
    return customerService.serviceremoveCustomer(customerId);
}


@PutMapping("/{customerId}")
@ResponseBody
public Customer serviceupdateCustomer(Customer customer){
    logger.info("customer service was instalized for update method");
    return customerService.serviceupdateCustomer(customer);
}


@GetMapping("/{customerId}")
@ResponseBody
public Customer servicegetCustomerById(int customerId){
    logger.info("customer service was instalized for getCustomerById");
    return customerService.servicegetCustomerById(customerId);
}


}