package com.sprint2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint2.Exceptions.InvalidOperation;
import com.sprint2.controller.OrderController;
import com.sprint2.model.Order;
import com.sprint2.service.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
	@Mock
	OrderService orderservice;
	
	@InjectMocks
	OrderController orderController;

	@Test
	public void update()throws InvalidOperation
	{
		Order order=new Order("eeg","5","ddgg");
		Mockito.when(orderController.updateOrder(order)).thenReturn(order);
		assertEquals(order,orderController.updateOrder(order));

	}
	@Test
    public void getall() {
        List<Order> order=new ArrayList<Order>();
        Mockito.when(orderController.getAllOrder()).thenReturn(order);
        assertEquals(order,orderController.getAllOrder());

    }
	@Test
	public void addOrder() 
	{
		Order p1=new Order("dfgh","5","sfdghyujy");
		Mockito.when(orderController.addOrder(p1)).thenReturn(p1);
	    assertEquals(p1,orderController.addOrder(p1));
	}
	@Test
    public void get() {
		Order order=new Order();
		
        Mockito.when(orderController.getOrderByorderNumber(26)).thenReturn(order);
        assertEquals(order,orderController.getOrderByorderNumber(26));

    }
	@Test
			public void testdelete() {
		 	boolean str1=true;;
		 	int id=29;
		 	Mockito.when(orderController.deleteOrderbyorderNumber(id)).thenReturn(str1);
		 	assertEquals(str1,orderController.deleteOrderbyorderNumber(id));
	}


}
