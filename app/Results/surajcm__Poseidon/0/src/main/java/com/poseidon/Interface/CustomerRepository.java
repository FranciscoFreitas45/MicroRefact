package com.poseidon.Interface;
public interface CustomerRepository {

   public List<Customer> findByName(String name);
   public Object findById(Object Object);
}