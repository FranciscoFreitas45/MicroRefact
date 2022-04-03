package com.cg.hbm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.hbm.entites.User;
import com.cg.hbm.service.IUserService;


@SpringBootTest
public class UserTest extends AbstractTest {
	
	@Autowired
	IUserService uService;
	

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */

	@Test
	public void getUser() throws Exception {
		String uri = "/user/5";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User user = super.mapFromJson(content, User.class);
		assertEquals("Navin", user.getUser_name());
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void addUser() throws Exception{
	User user=new User();

	user.setAddress("Jubili hills");;
	user.setEmail("ho@gmail.com");
	user.setMobile("9099009090");
	user.setPassword("n@127");
	user.setRole("user");
	user.setUser_name("Navin");
	String uri="/user/add";
	String inputJson=super.mapToJson(user);
	MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
    int status=mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content=mvcResult.getResponse().getContentAsString();
    User u=super.mapFromJson(content, User.class);
    assertEquals("ho@gmail.com",u.getEmail());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	 
	@Test
	public void getShowAllUsers() throws Exception {
		String uri = "/user/all";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User user[] = super.mapFromJson(content, User[].class);
		assertEquals("Navin", user[0].getUser_name());
	}
	
	/**
	 * 
	 * @throws Exception
	 */

	@Test
	public void updateUser() throws Exception{
	String uri="/user/36";
	String putUri="/user/user";
	MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
    int status=mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content=mvcResult.getResponse().getContentAsString();
    User u=super.mapFromJson(content, User.class);
    u.setUser_name("Navin");
    u.setPassword("hindi");
    String inputJson = super.mapToJson(u);
	MvcResult mvcResult1=mvc.perform(MockMvcRequestBuilders.put(putUri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
    int status1 = mvcResult1.getResponse().getStatus();
    assertEquals(200,status1);
    String content1 = mvcResult1.getResponse().getContentAsString();
    User u1=super.mapFromJson(content1, User.class);
    assertEquals("Navin",u1.getUser_name());
	assertEquals("hindi",u1.getPassword());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteUser() throws Exception{
		 this.mvc.perform(MockMvcRequestBuilders.delete("/user/22").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}


}
