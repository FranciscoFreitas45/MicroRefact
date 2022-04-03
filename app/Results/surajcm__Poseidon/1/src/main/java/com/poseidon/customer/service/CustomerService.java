package com.poseidon.customer.service;
 import com.poseidon.customer.dao.CustomerDAO;
import com.poseidon.customer.domain.CustomerVO;
import com.poseidon.customer.exception.CustomerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {

 private  Logger LOG;

 private  CustomerDAO customerDAO;

public CustomerService(final CustomerDAO customerDAO) {
    this.customerDAO = customerDAO;
}
public CustomerVO saveCustomer(CustomerVO currentCustomerVO){
    return customerDAO.saveCustomer(currentCustomerVO);
}


public void deleteCustomerFromId(Long id){
    customerDAO.deleteCustomerFromId(id);
}


public List<CustomerVO> listAllCustomerDetails(){
    return customerDAO.listAllCustomerDetails();
}


public void updateCustomer(CustomerVO currentCustomerVO){
    try {
        customerDAO.updateCustomer(currentCustomerVO);
    } catch (CustomerException ex) {
        LOG.error(ex.getMessage());
    }
}


public Optional<CustomerVO> getCustomerFromId(Long id){
    return customerDAO.getCustomerFromId(id);
}


public List<CustomerVO> searchCustomer(CustomerVO searchCustomerVO){
    return customerDAO.searchCustomer(searchCustomerVO);
}


}