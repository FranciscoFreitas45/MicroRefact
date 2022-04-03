package com.poseidon.customer.dao;
 import com.poseidon.customer.dao.entities.Customer;
import com.poseidon.customer.dao.entities.CustomerAdditionalDetails;
import com.poseidon.customer.dao.repo.CustomerAdditionalDetailsRepository;
import com.poseidon.customer.dao.repo.CustomerRepository;
import com.poseidon.customer.dao.spec.CustomerSpecification;
import com.poseidon.customer.domain.CustomerAdditionalDetailsVO;
import com.poseidon.customer.domain.CustomerVO;
import com.poseidon.customer.exception.CustomerException;
import com.poseidon.init.specs.SearchCriteria;
import com.poseidon.init.specs.SearchOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import com.rainerhahnekamp.sneakythrow.Sneaky.sneak;
import com.rainerhahnekamp.sneakythrow.Sneaky.sneaked;
@Service
@SuppressWarnings("unused")
public class CustomerDAO {

 private  Logger LOG;

 private  String MOBILE;

 private  CustomerRepository customerRepository;

 private  CustomerAdditionalDetailsRepository customerAdditionalDetailsRepository;

@PersistenceContext
 private  EntityManager em;

public CustomerDAO(final CustomerRepository customerRepository, final CustomerAdditionalDetailsRepository customerAdditionalDetailsRepository) {
    this.customerRepository = customerRepository;
    this.customerAdditionalDetailsRepository = customerAdditionalDetailsRepository;
}
public SearchOperation populateSearchOperation(CustomerVO searchVO){
    SearchOperation searchOperation;
    if (searchVO.getIncludes() != null && Boolean.TRUE.equals(searchVO.getIncludes())) {
        searchOperation = SearchOperation.MATCH;
    } else if (searchVO.getStartsWith() != null && Boolean.TRUE.equals(searchVO.getStartsWith())) {
        searchOperation = SearchOperation.MATCH_START;
    } else {
        searchOperation = SearchOperation.EQUAL;
    }
    return searchOperation;
}


public void deleteCustomerFromId(Long id){
    deleteAdditionalDetails(id);
    var consumer = sneaked(customerRepository::deleteById);
    consumer.accept(id);
}


public void saveAdditionalDetails(CustomerVO currentCustomerVo,Customer newCustomer){
    var additionalDetails = setAdditionalDetailsToVO(currentCustomerVo);
    currentCustomerVo.setCustomerAdditionalDetailsVO(additionalDetails);
    var newAdditionalDetails = convertToCustomerAdditionalDetails(newCustomer.getId(), currentCustomerVo.getCustomerAdditionalDetailsVO());
    sneak(() -> customerAdditionalDetailsRepository.save(newAdditionalDetails));
}


public Optional<CustomerAdditionalDetails> getAdditionalDetailsOfCustomerId(Long id){
    return sneak(() -> customerAdditionalDetailsRepository.findByCustomerId(id));
}


public Optional<CustomerVO> getCustomerFromId(Long id){
    return sneak(() -> customerRepository.findById(id)).map(this::convertToSingleCustomerVO).map(this::getAdditionalDetailsToVO);
}


public CustomerVO getAdditionalDetailsToVO(CustomerVO customerVO){
    var additionalDetails = getAdditionalDetailsOfCustomerId(customerVO.getCustomerId());
    additionalDetails.ifPresent(customerAdditionalDetails -> updateCustomerWithAdditionalDetails(customerVO, customerAdditionalDetails));
    return customerVO;
}


public CustomerAdditionalDetails buildNew(Long id){
    CustomerAdditionalDetails customerAdditionalDetails = new CustomerAdditionalDetails();
    customerAdditionalDetails.setCustomerId(id);
    return customerAdditionalDetails;
}


public CustomerAdditionalDetailsVO setAdditionalDetailsToVO(CustomerVO currentCustomerVo){
    var additionalDetails = new CustomerAdditionalDetailsVO();
    additionalDetails.setContactPerson(currentCustomerVo.getContactPerson());
    additionalDetails.setContactMobile(currentCustomerVo.getContactMobile());
    additionalDetails.setNotes(currentCustomerVo.getNotes());
    additionalDetails.setCreatedBy(currentCustomerVo.getCreatedBy());
    additionalDetails.setModifiedBy(currentCustomerVo.getModifiedBy());
    return additionalDetails;
}


public List<CustomerVO> searchCustomer(CustomerVO searchCustomerVo){
    return searchCustomerInDetail(searchCustomerVo);
}


public boolean isAdditionalDetailsPresent(CustomerVO currentCustomerVo){
    return currentCustomerVo.getContactPerson() != null || currentCustomerVo.getContactMobile() != null || currentCustomerVo.getNotes() != null;
}


public void updateAdditionalDetails(CustomerVO currentCustomerVo,CustomerAdditionalDetails customerAdditionalDetails){
    customerAdditionalDetails.setContactPerson(currentCustomerVo.getContactPerson());
    customerAdditionalDetails.setContactPhone(currentCustomerVo.getContactMobile());
    customerAdditionalDetails.setNote(currentCustomerVo.getNotes());
}


public Customer convertToSingleCustomer(CustomerVO currentCustomerVO){
    var customer = new Customer();
    customer.setName(currentCustomerVO.getCustomerName());
    customer.setAddress(currentCustomerVO.getAddress());
    customer.setPhone(currentCustomerVO.getPhoneNo());
    customer.setMobile(currentCustomerVO.getMobile());
    customer.setEmail(currentCustomerVO.getEmail());
    customer.setCreatedBy(currentCustomerVO.getCreatedBy());
    customer.setModifiedBy(currentCustomerVO.getModifiedBy());
    return customer;
}


public Optional<Customer> getCustomer(Long id){
    return sneak(() -> customerRepository.findById(id));
}


public CustomerVO saveCustomer(CustomerVO currentCustomerVo){
    var customer = convertToSingleCustomer(currentCustomerVo);
    var newCustomer = sneak(() -> customerRepository.save(customer));
    saveAdditionalDetails(currentCustomerVo, newCustomer);
    return convertToSingleCustomerVO(newCustomer);
}


public void deleteAdditionalDetails(Long id){
    var additionalDetails = getAdditionalDetailsOfCustomerId(id);
    additionalDetails.ifPresent(details -> customerAdditionalDetailsRepository.deleteById(details.getId()));
}


public void updateCustomerWithCustomerVo(CustomerVO currentCustomerVo,Customer customer){
    customer.setName(currentCustomerVo.getCustomerName());
    customer.setAddress(currentCustomerVo.getAddress());
    customer.setPhone(currentCustomerVo.getPhoneNo());
    customer.setMobile(currentCustomerVo.getMobile());
    customer.setEmail(currentCustomerVo.getEmail());
    customer.setModifiedBy(currentCustomerVo.getModifiedBy());
}


public void updateCustomerWithAdditionalDetails(CustomerVO customerVO,CustomerAdditionalDetails customerAdditionalDetails){
    customerVO.setContactPerson(customerAdditionalDetails.getContactPerson());
    customerVO.setContactMobile(customerAdditionalDetails.getContactPhone());
    customerVO.setNotes(customerAdditionalDetails.getNote());
}


public CustomerVO convertToSingleCustomerVO(Customer customer){
    var customerVO = new CustomerVO();
    customerVO.setCustomerId(customer.getId());
    customerVO.setCustomerName(customer.getName());
    customerVO.setAddress(customer.getAddress());
    customerVO.setPhoneNo(customer.getPhone());
    customerVO.setMobile(customer.getMobile());
    customerVO.setEmail(customer.getEmail());
    customerVO.setCreatedBy(customer.getCreatedBy());
    customerVO.setModifiedBy(customer.getModifiedBy());
    return customerVO;
}


public CustomerAdditionalDetails convertToCustomerAdditionalDetails(Long customerId,CustomerAdditionalDetailsVO customerAdditionalDetailsVO){
    var additionalDetails = new CustomerAdditionalDetails();
    additionalDetails.setCustomerId(customerId);
    if (customerAdditionalDetailsVO != null) {
        additionalDetails.setContactPerson(customerAdditionalDetailsVO.getContactPerson());
        additionalDetails.setContactPhone(customerAdditionalDetailsVO.getContactMobile());
        additionalDetails.setNote(customerAdditionalDetailsVO.getNotes());
        additionalDetails.setCreatedBy(customerAdditionalDetailsVO.getCreatedBy());
        additionalDetails.setModifiedBy(customerAdditionalDetailsVO.getModifiedBy());
    }
    return additionalDetails;
}


public List<CustomerVO> listAllCustomerDetails(){
    List<Customer> customers = sneak(customerRepository::findAll);
    return convertToCustomerVO(customers);
}


public List<CustomerVO> searchCustomerInDetail(CustomerVO searchVO){
    var customerSpec = new CustomerSpecification();
    var search = populateSearchOperation(searchVO);
    if (!ObjectUtils.isEmpty(searchVO.getCustomerId())) {
        customerSpec.add(new SearchCriteria("customerId", searchVO.getCustomerId(), search));
    }
    if (!StringUtils.hasText(searchVO.getCustomerName())) {
        customerSpec.add(new SearchCriteria("name", searchVO.getCustomerName(), search));
    }
    if (!StringUtils.hasText(searchVO.getMobile())) {
        customerSpec.add(new SearchCriteria(MOBILE, searchVO.getMobile(), search));
    }
    List<Customer> resultCustomers = sneak(() -> customerRepository.findAll(customerSpec));
    return convertToCustomerVO(resultCustomers);
}


public void updateCustomer(CustomerVO currentCustomerVo){
    var optionalCustomer = getCustomer(currentCustomerVo.getCustomerId());
    if (optionalCustomer.isPresent()) {
        var customer = optionalCustomer.get();
        updateCustomerWithCustomerVo(currentCustomerVo, customer);
        sneak(() -> customerRepository.save(customer));
        if (isAdditionalDetailsPresent(currentCustomerVo)) {
            CustomerAdditionalDetails customerAdditionalDetails = populateDetails(customer);
            updateAdditionalDetails(currentCustomerVo, customerAdditionalDetails);
            sneak(() -> customerAdditionalDetailsRepository.save(customerAdditionalDetails));
        }
    }
}


public List<CustomerVO> convertToCustomerVO(List<Customer> customers){
    return customers.stream().map(this::convertToSingleCustomerVO).toList();
}


public CustomerAdditionalDetails populateDetails(Customer customer){
    var additionalDetails = getAdditionalDetailsOfCustomerId(customer.getId());
    return additionalDetails.orElseGet(() -> this.buildNew(customer.getId()));
}


}