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

import com.cg.oms.model.Role;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoleControllerIntegrationTest
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
	void testAddRole()
	{
		Role role = new Role();
		role.setRoleName("admin");
		ResponseEntity<Role> postResponse = restTemplate.postForEntity(getRootUrl() + "/Role/newRole", role,
				Role.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	void testGetAllRole()
	{
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/Roles/all", HttpMethod.GET, entity,
				String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	void testRoleById()
	{
		Role role = restTemplate.getForObject(getRootUrl() + "/Role/104", Role.class);
		System.out.println(role.getRoleId() + " " + role.getRoleName());
		assertNotNull(role);
	}

	@Test
	void testUpdateRole()
	{
		Role role = restTemplate.getForObject(getRootUrl() + "/Role/101", Role.class);
		// assertEquals(role.getRoleId(), 101);
		role.setRoleName("admin");
		restTemplate.put(getRootUrl() + "/Roles/updateRole/101", role);
		Role updatedRole = restTemplate.getForObject(getRootUrl() + "/Role/101", Role.class);
		assertNotNull(updatedRole);
	}

	@Test
	void testDeleteRole()
	{
		Role role = restTemplate.getForObject(getRootUrl() + "/Role/101", Role.class);
		// assertNotNull(Role.getIds(),101);
		restTemplate.delete(getRootUrl() + "/Roles/deleteRole/101");

		Role role1 = restTemplate.getForObject(getRootUrl() + "/Role/101", Role.class);
		System.out.println(role);
		assertNotEquals(role, role1);

	}

}
