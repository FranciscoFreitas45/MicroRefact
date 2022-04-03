package com.sprint2.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint2.Exceptions.CustomerException;
import com.sprint2.model.Customer;
import com.sprint2.repository.CustomerRepository;
import com.sprint2.utility.Validations;
@Service
public class CustomerService implements ICustomerService{

@Autowired
 private  CustomerRepository customerRepository;


public Customer serviceaddCustomer(Customer customer){
    if (customer.getCustomerPassword().matches(Validations.password) && customer.getCustomerEmail().matches(Validations.email) && customer.getCustomerName().matches(Validations.nameregex) && customer.getCustomerPostalcode().matches(Validations.postalCode) && customer.getCustomerContact().matches(Validations.contact))
        return customerRepository.save(customer);
    else
        throw new CustomerException("The above given customer details are not valid");
/*catch(CustomerException e)
		{
			System.out.println(e);
		}
		return customer;*/
}


public List<Customer> servicegetAllCustomer(){
    return customerRepository.findAll();
}


public String serviceremoveCustomer(int customerId){
    Optional<Customer> repCustomer = customerRepository.findById(customerId);
    try {
        if (repCustomer.isPresent()) {
            customerRepository.deleteById(customerId);
            return "Customer deleted successfully";
        } else
            throw new CustomerException("Enter the valid customerId to  remove it");
    } catch (Exception e) {
        // throw new CustomerException("No details are not found");
        System.out.println(e);
        return ("CustomerId is not found");
    }
}


public Customer serviceupdateCustomer(Customer customer){
    if (customer.getCustomerEmail().matches(Validations.email) && customer.getCustomerPassword().matches(Validations.password) && customer.getCustomerPostalcode().matches(Validations.postalCode) && customer.getCustomerContact().matches(Validations.contact)) {
        return customerRepository.save(customer);
    } else {
        throw new CustomerException("Customer with the given id is not found to update it");
    }
}


public Customer servicegetCustomerById(int customerId){
    try {
        return customerRepository.findById(customerId).get();
    } catch (CustomerException e) {
        e.printStackTrace();
        return null;
    }
}


}