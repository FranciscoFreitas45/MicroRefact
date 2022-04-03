package io.delivery.service;
 import io.delivery.entity.Customer;
import java.util.List;
public interface CustomerService {


public Customer getCustomerById(long id)
;

public List<Customer> getAllCustomers()
;

public Customer createCustomer(Customer customer)
;

public List<Customer> getCustomerByName(String name)
;

public Customer updateCustomer(Customer customer)
;

public Customer deleteCustomer(long id)
;

public List<Customer> getCustomerByAddress(String address)
;

public Customer getCustomerByEmail(String eMail)
;

public Customer getCustomerByPhoneNumber(String phoneNumber)
;

}