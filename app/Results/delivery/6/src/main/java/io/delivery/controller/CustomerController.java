package io.delivery.controller;
 import io.delivery.entity.Customer;
import io.delivery.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation;
import java.util.List;
@Controller
@RequestMapping("/customer")
public class CustomerController {

 final  CustomerService customerService;


@RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
@ResponseBody
public Customer addCustomer(Customer customer){
    customerService.createCustomer(customer);
    return customer;
}


@RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
@ResponseBody
public Customer getCustomerById(String id){
    return customerService.getCustomerById(Long.parseLong(id));
}


@RequestMapping(value = "/delete/id/{id}", method = RequestMethod.DELETE)
@ResponseBody
public Customer deleteCustomerById(String id){
    return customerService.deleteCustomer(Long.parseLong(id));
}


@RequestMapping(value = "/get/all", method = RequestMethod.GET)
@ResponseBody
public List<Customer> getAllCustomers(){
    return customerService.getAllCustomers();
}


@RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
@ResponseBody
public List<Customer> getCustomerByName(String name){
    return customerService.getCustomerByName(name);
}


@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@ResponseBody
public Customer updateCustomer(Customer customer){
    customerService.updateCustomer(customer);
    return customer;
}


@RequestMapping(value = "/get/email/{eMail}", method = RequestMethod.GET)
@ResponseBody
public Customer getCustomerByEmail(String eMail){
    return customerService.getCustomerByEmail(eMail);
}


@RequestMapping(value = "/get/address/{address}", method = RequestMethod.GET)
@ResponseBody
public List<Customer> getCustomerByAddress(String address){
    return customerService.getCustomerByAddress(address);
}


@RequestMapping(value = "/get/phone/{phoneNumber}", method = RequestMethod.GET)
@ResponseBody
public Customer getCustomerByPhoneNumber(String phoneNumber){
    return customerService.getCustomerByPhoneNumber(phoneNumber);
}


}