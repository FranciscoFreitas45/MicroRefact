package guru.springframework.services;
 import java.util.List;
import guru.springframework.domain.Customer;
import guru.springframework.domain.PackageType;
import guru.springframework.domain.User;
public interface CustomerService {


public List<Customer> getAllCustomers()
;

public Customer findCustomerById(Integer id)
;

}