package com.sprint2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.sprint2.controller.UserController;
import com.sprint2.model.User;
import com.sprint2.service.UserService;



@ExtendWith(MockitoExtension.class)    //ApplicationTests class extends MockitoExtension class
public class UserTests {
	private MockMvc mockMvc;
	@Mock            //This annotation is used to craete mocked instances automatically.
	UserService userServices;

	@InjectMocks         //This annotation is used to inject mock fields into the tested object automatically 
	UserController userController;
	@Test
	void testInsertUser() {
		User user = new User("janani","karthikA123","admin");
		Mockito.when(userController.insertUser(user)).thenReturn(user);
		assertEquals(user, userController.insertUser(user));
		assertEquals(user.getUserName(),userController.insertUser(user).getUserName());
		assertEquals(user.getUserPassword(),userController.insertUser(user).getUserPassword());
		assertEquals(user.getRole(),userController.insertUser(user).getRole());
	}
	@Test
	void testLogoutUser() {
		User user = new User("janani","karthikA123","admin");
		String str=user.getUserName()+" has logged out successfully";
		Mockito.when(userController.logout(user)).thenReturn(str);
	    assertEquals(str,userController.logout(user));
	}
	@Test
	void testLoginUser() {
		User user = new User("janani","karthikA123","admin");
		String str=user.getUserName()+" has successfully logged in";
		Mockito.when(userController.logout(user)).thenReturn(str);
	    assertEquals(str,userController.logout(user));
	}
	@Test
	void testdeleteUser() {
		 boolean b= true;
		 String userName="janani";
		 Mockito.when(userController.deleteUser(userName)).thenReturn(b);
	     assertEquals(b,userController.deleteUser(userName));
	}
}

