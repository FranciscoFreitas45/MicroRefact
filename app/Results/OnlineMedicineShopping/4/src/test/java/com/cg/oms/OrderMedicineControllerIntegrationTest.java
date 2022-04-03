package com.cg.oms;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.cg.oms.model.OrderMedicine;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderMedicineControllerIntegrationTest
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
	void testAddOrderMedicine()
	{
		OrderMedicine orderMedicine = new OrderMedicine();
		orderMedicine.setOrder(null);
		orderMedicine.setMedicineList(null);
		orderMedicine.setQuantity(30);
		orderMedicine.setPrice(55.22);
		ResponseEntity<OrderMedicine> postResponse = restTemplate.postForEntity(getRootUrl() + "/Ordermedicine/addnew",
				orderMedicine, OrderMedicine.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	void testGetAllOrderMedicine()
	{
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/OrderMedicines/all", HttpMethod.GET,
				entity, String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	void testOrderMedicineById()
	{
		OrderMedicine orderMedicine = restTemplate.getForObject(getRootUrl() + "/OrderMedicine/104",
				OrderMedicine.class);
		System.out.println(orderMedicine.getOrderMedicineId() + " " + orderMedicine.getMedicineList() + " "
				+ orderMedicine.getQuantity() + " " + orderMedicine.getPrice());
		assertNotNull(orderMedicine);
	}

	@Test
	void testUpdateOrderMedicine()
	{
		OrderMedicine orderMedicine = restTemplate.getForObject(getRootUrl() + "/OrderMedicine/101",
				OrderMedicine.class);

		restTemplate.put(getRootUrl() + "/OrderMedicines/updateOrderMedicine/101", orderMedicine);
		OrderMedicine updatedOrderMedicine = restTemplate.getForObject(getRootUrl() + "/OrderMedicine/101",
				OrderMedicine.class);
		assertNotNull(updatedOrderMedicine);
	}

	@Test
	void testDeleteOrderMedicine()
	{
		OrderMedicine orderMedicine = restTemplate.getForObject(getRootUrl() + "/OrderMedicine/101",
				OrderMedicine.class);
		// assertNotNull(OrderMedicine.getIds(),101);
		restTemplate.delete(getRootUrl() + "/OrderMedicines/deleteOrderMedicine/101");
		OrderMedicine OrderMedicine1 = restTemplate.getForObject(getRootUrl() + "/OrderMedicine/101",
				OrderMedicine.class);
		System.out.println(orderMedicine);
		assertNotEquals(orderMedicine, OrderMedicine1);

	}

}
