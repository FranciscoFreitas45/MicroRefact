package com.sprint2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sprint2.controller.CustomerController;
import com.sprint2.model.Customer;
import com.sprint2.service.CustomerService;


@ExtendWith(MockitoExtension.class)
//@PrepareForTest(Cu)
public class SpringSwaggerDemoApplicationTests {
	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController ;
	
	
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	 @Test
	    public void testGetAllCustomer()
	    {
	        List<Customer> customer=new ArrayList<Customer>();
	       Mockito.when(customerController.servicegetAllCustomer()).thenReturn(customer);
	        assertEquals(customer,customerController.servicegetAllCustomer());

	    }
	 @Test
	 public void testCustomerById()
	 {
		Customer customer=new Customer();
		 int customerId=121;
		 Mockito.when(customerController.servicegetCustomerById(customerId)).thenReturn(customer);
	        assertEquals(customer,customerController.servicegetCustomerById(customerId));
		 
	 }
	
	 @Test
	 public void testaddCustomer() 
	 {
		Customer d1=new Customer("Anil","anil@gmail.com","Anil@09","America","tpt","456789","7981770453");
	 
	    		Mockito.when(customerController.serviceaddCustomer(d1)).thenReturn(d1);
	    	    assertEquals(d1,customerController.serviceaddCustomer(d1));
	   
	 }
	 
	 @Test
	 public void testupdateCustomer() 
	 {
		 Customer d1=new Customer("Anil","anil@gmail.com","Anil@09","America","tpt","456789","7890123456");
		 Mockito.when(customerController.serviceupdateCustomer(d1)).thenReturn(d1);
	        assertEquals(d1,customerController.serviceupdateCustomer( d1));
		 
	 }
	 @Test
	 public void testdeleteCustomer()
	 {
		 String str1="Customer deleted Successfully";
		 int customerId=146;
		Mockito.when(customerController.serviceremoveCustomer(customerId)).thenReturn(str1);
	        assertEquals(str1,customerController.serviceremoveCustomer(customerId));
	 }

}
