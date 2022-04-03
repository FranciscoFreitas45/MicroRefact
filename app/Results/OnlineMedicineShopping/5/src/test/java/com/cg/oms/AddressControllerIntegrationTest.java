
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

import com.cg.oms.model.Address;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerIntegrationTest
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
	void testAddAddress()
	{
		Address address = new Address();
		address.setFlatNo("NO:48");
		address.setStreetName("Nehru Colony");
		address.setArea("North Rampart");
		address.setCity("Thanjavur");
		address.setState("TamilNadu");
		address.setPinCode(613009);
		ResponseEntity<Address> postResponse = restTemplate.postForEntity(getRootUrl() + "/address/newaddress", address,
				Address.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	void testGetAllAddress()
	{
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/addresss/all", HttpMethod.GET, entity,
				String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	void testAddressById()
	{
		Address address = restTemplate.getForObject(getRootUrl() + "/address/104", Address.class);
		System.out.println(address.getAddressId() + " " + address.getFlatNo() + " " + address.getStreetName() + " "
				+ address.getArea() + " " + address.getCity() + " " + address.getState() + " " + address.getPinCode());
		assertNotNull(address);
	}

	@Test
	void testUpdateAddress()
	{
		Address address = restTemplate.getForObject(getRootUrl() + "/address/101", Address.class);
		// assertEquals(address.getAddressId(), 101);
		System.out.println("addressId: " + address.getAddressId());
		address.setFlatNo("NO:49");
		address.setStreetName("Mahesh Colony");
		address.setArea("West Rampart");
		address.setCity("Trichy");
		address.setState("TamilNadu");
		address.setPinCode(612548);
		restTemplate.put(getRootUrl() + "/addresss/updateaddress/101", address);
		Address updatedAddress = restTemplate.getForObject(getRootUrl() + "/address/101", Address.class);
		assertNotNull(updatedAddress);
	}

	@Test
	void testDeleteAddress()
	{
		Address address = restTemplate.getForObject(getRootUrl() + "/address/101", Address.class);
		// assertNotNull(address.getIds(),101);
		restTemplate.delete(getRootUrl() + "/addresss/deleteaddress/101");

		Address address1 = restTemplate.getForObject(getRootUrl() + "/address/101", Address.class);
		System.out.println(address);
		assertNotEquals(address, address1);

	}

}
