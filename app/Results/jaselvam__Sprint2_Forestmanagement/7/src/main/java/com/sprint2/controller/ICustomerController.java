package com.sprint2.controller;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Customer;
public interface ICustomerController {


public Customer serviceaddCustomer(Customer customer)
;

public List<Customer> servicegetAllCustomer()
;

public String serviceremoveCustomer(int customerId)
;

public Customer serviceupdateCustomer(Customer customer)
;

public Customer servicegetCustomerById(int customerId)
;

}