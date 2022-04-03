
package com.cg.oms;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.cg.oms.model.Order;
import com.cg.oms.DTO.User;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIntegrationTest
{
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	private String getRootUrl()
	{
		return "http://localhost:" + port;
	}

	@Test
	void testAddOrder()
	{
		Order order = new Order();
		LocalDateTime localDateTime = LocalDateTime.now();
		order.setUser(new User());
		order.setOrderDate(localDateTime);
		order.setAddress("North street");
		ResponseEntity<Order> postResponse = restTemplate.postForEntity(getRootUrl() + "/Order/newOrder", order,
				Order.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	void testGetAllOrder()
	{
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/Orders/all", HttpMethod.GET, entity,
				String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	void testOrderById()
	{
		Order order = restTemplate.getForObject(getRootUrl() + "/Order/104", Order.class);
		System.out.println(
				order.getOrderId() + " " + order.getUser() + " " + order.getOrderDate() + " " + order.getAddress());
		assertNotNull(order);
	}

	@Test
	void testUpdateOrder()
	{
		Order order = restTemplate.getForObject(getRootUrl() + "/Order/101", Order.class);
//		assertEquals(order.getOrderId(), 101);
		LocalDateTime localDateTime = LocalDateTime.now();
		order.setUser(new User());
		order.setOrderDate(localDateTime);
		order.setAddress("NorthStreet");
		restTemplate.put(getRootUrl() + "/Orders/updateOrder/101", order);
		Order updatedOrder = restTemplate.getForObject(getRootUrl() + "/Order/101", Order.class);
		assertNotNull(updatedOrder);
	}

	@Test
	void testDeleteOrder()
	{
		Order order = restTemplate.getForObject(getRootUrl() + "/Order/101", Order.class);
		// assertNotNull(Order.getIds(),101);
		restTemplate.delete(getRootUrl() + "/Orders/deleteOrder/101");
		Order order1 = restTemplate.getForObject(getRootUrl() + "/Order/101", Order.class);
		System.out.println(order);
		assertNotEquals(order, order1);
	}

}
