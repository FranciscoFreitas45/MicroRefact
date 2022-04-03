package com.poseidon.Interface;
public interface CustomerService {

   public CustomerVO saveCustomer(CustomerVO currentCustomerVO);
   public Optional<CustomerVO> getCustomerFromId(Long id);
}