package guru.springframework.services;
 import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import guru.springframework.domain.Customer;
import guru.springframework.domain.PackageType;
import guru.springframework.domain.Role;
import guru.springframework.domain.User;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.repositories.UserRepository;
@Service
public class CustomerServiceImpl implements CustomerService{

@Autowired
 private UserRepository userRepository;

@Autowired
 private RoleRepository roleRepository;

@Autowired
 private CustomerRepository customerRepository;

 private BCryptPasswordEncoder bCryptPasswordEncoder;


@Override
public List<Customer> getAllCustomers(){
    return customerRepository.findAll();
}


@Override
public Customer findCustomerById(Integer id){
    return customerRepository.findCustomerById(id);
}


}