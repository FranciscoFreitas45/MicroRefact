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

import com.cg.oms.model.User;
import com.cg.oms.DTO.Role;
import com.cg.oms.DTO.Address;

/**
 * 
 * @author Arivazhagan
 *
 */
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest
{
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	/**
	 * 
	 * @return localhost with the defined tomcat port.
	 */
	private String getRootUrl()
	{
		return "http://localhost:" + port;
	}

	/**
	 * Testing whether the User gets added to the database.
	 * 
	 */
	@Test
	void testAddUser()
	{
		User user = new User();
		Role role = new Role("med");
		role.setRoleId(1);
		Address address = new Address();
		address.setAddressId(1);
		LocalDateTime localDateTime = LocalDateTime.now();
		user.setEmailId("vino@gmail.com");
		user.setFirstName("vino");
		user.setLastName("vino");
		user.setUserGender("male");
		user.setUserPhoneNumber("9442871261");
		user.setUserAge(20);
		user.setUserPassword("vino123");
		user.setPreviousPassword1("vino223");
		user.setPreviousPassword2("vino888");
		user.setCreatedDate(localDateTime);
		user.setRole(role);
		user.setUserAddress(address);
//		user.setUserName("admin");
		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/User/newUser", 
							user,
							User.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	/**
	 * Testing whether the User database has users or nill.
	 * 
	 */
	@Test
	void testGetAllUser()
	{
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/Users/all", HttpMethod.GET, entity,
				String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	/**
	 * Testing whether the given id fetches the given user or not.
	 * 
	 */
	@Test
	void testUserById()
	{
		User user = restTemplate.getForObject(getRootUrl() + "/User/104", User.class);
		System.out.println(user.getEmailId() + " " + user.getFirstName() + " " + user.getFirstName() + " "
				+ user.getUserGender() + " " + user.getUserPhoneNumber() + " " + user.getUserAge() + " "
				+ user.getUserPassword() + " " + user.getPreviousPassword1() + " " + user.getPreviousPassword2()
				+ user.getCreatedDate() + " " + user.getRole() + " " + user.getUserAddress());
		assertNotNull(user);
	}

	/**
	 * Testing whether the User gets updated to the database.
	 * 
	 */
	@Test
	void testUpdateUser()
	{
		User user = restTemplate.getForObject(getRootUrl() + "/User/101", User.class);
		Role role = new Role("med");
		role.setRoleId(1);
		Address address = new Address();
		address.setAddressId(1);
		LocalDateTime localDateTime = LocalDateTime.now();
		user.setEmailId("vino@gmail.com");
		user.setFirstName("vino");
		user.setLastName("vino");
		user.setUserGender("male");
		user.setUserPhoneNumber("9442871261");
		user.setUserAge(20);
		user.setUserPassword("vino123");
		user.setPreviousPassword1("vino223");
		user.setPreviousPassword2("vino888");
		user.setCreatedDate(localDateTime);
		user.setRole(role);
		user.setUserAddress(address);
//		user.setUserName("admin");
		restTemplate.put(getRootUrl() + "/Users/updateUser/101", user);
		User updatedUser = restTemplate.getForObject(getRootUrl() + "/User/101", User.class);
		assertNotNull(updatedUser);
	}

	/**
	 * Testing whether the User gets removed from the database.
	 * 
	 */
	@Test
	void testDeleteUser()
	{
		User user = restTemplate.getForObject(getRootUrl() + "/User/101", User.class);
		restTemplate.delete(getRootUrl() + "/Users/deleteUser/101");

		User user1 = restTemplate.getForObject(getRootUrl() + "/User/101", User.class);
		System.out.println(user);
		assertNotEquals(user, user1);

	}

}
