package io.delivery.dao;
 import io.delivery.entity.Customer;
import java.util.List;
public interface CustomerDao extends BasicDao<Customer>{


public Customer findCustomerByEmail(String eMail)
;

public List<Customer> findCustomerByName(String name)
;

public Customer findCustomerByPhoneNumber(String phoneNumber)
;

public List<Customer> findCustomerByAddress(String address)
;

}