package com.sprint2.service;
 import java.util.List;
import com.sprint2.model.Customer;
public interface ICustomerService {


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