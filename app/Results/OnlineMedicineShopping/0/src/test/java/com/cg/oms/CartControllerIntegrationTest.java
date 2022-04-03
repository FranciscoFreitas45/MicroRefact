
package com.cg.oms;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.cg.oms.model.Cart;
import com.cg.oms.DTO.Medicine;
import com.cg.oms.DTO.User;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerIntegrationTest
{
	@Autowired
	User user = new User();
	@Autowired
	List<Medicine> medicine = new ArrayList<Medicine>();
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl()
	{
		return "http://localhost:" + port;
	}

	@Test
	void testSaveCart()
	{
		Cart cart = new Cart();
		cart.setMedicineList(medicine);
		cart.setUser(user);
		cart.setQuantity(10);
		cart.setCostPerPiece(10.5f);
		ResponseEntity<Cart> postResponse = restTemplate.postForEntity(getRootUrl() + "/Cart/newCart", cart,
				Cart.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	void testGetAllCart()
	{
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/Carts/all", HttpMethod.GET, entity,
				String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	void testCartById()
	{
		Cart cart = restTemplate.getForObject(getRootUrl() + "/Cart/104", Cart.class);
		System.out.println(cart.getCartId() + " " + cart.getMedicineList() + " " + cart.getUser() + " "
				+ cart.getQuantity() + " " + cart.getCostPerPiece());
		assertNotNull(cart);
	}

	@Test
	void testUpdateCart()
	{
		Cart cart = restTemplate.getForObject(getRootUrl() + "/Cart/101", Cart.class);
		// assertEquals(cart.getCartId(), 101);
		System.out.println("cartId: " + cart.getCartId());
		cart.setUser(user);
		cart.setQuantity(11);
		cart.setCostPerPiece(11.5f);
		restTemplate.put(getRootUrl() + "/Carts/updateCart/101", cart);
		Cart updatedCart = restTemplate.getForObject(getRootUrl() + "/Cart/101", Cart.class);
		assertNotNull(updatedCart);
	}

	@Test
	void testDeleteCart()
	{
		Cart cart = restTemplate.getForObject(getRootUrl() + "/Cart/101", Cart.class);
		// assertNotNull(Cart.getIds(),101);
		restTemplate.delete(getRootUrl() + "/Carts/deleteCart/101");

		Cart cart1 = restTemplate.getForObject(getRootUrl() + "/Cart/101", Cart.class);
		System.out.println(cart);
		assertNotEquals(cart, cart1);

	}

}
