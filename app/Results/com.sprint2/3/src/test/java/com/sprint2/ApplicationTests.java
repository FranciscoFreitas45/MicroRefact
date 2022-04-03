package com.sprint2;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.sprint2.controller.AdminController;
import com.sprint2.model.Admin;
import com.sprint2.service.AdminService;



@ExtendWith(MockitoExtension.class)    //ApplicationTests class extends MockitoExtension class
class ApplicationTests {
	private MockMvc mockMvc;
	@Mock            //This annotation is used to craete mocked instances automatically.
	AdminService adminServices;

	@InjectMocks   //This annotation is used to inject mock fields into the tested object automatically 
	AdminController adminController;
	@Test
	void testInsertAdmin() {
		Admin admin = new Admin("janani","karthikA123");
		Mockito.when(adminController.insertAdmin(admin)).thenReturn(admin);
		assertEquals(admin, adminController.insertAdmin(admin));
		assertEquals(admin.getAdminName(),adminController.insertAdmin(admin).getAdminName());
		assertEquals(admin.getAdminPassword(),adminController.insertAdmin(admin).getAdminPassword());
	}
	@Test
	void testAdminById() {
		Admin admin= new Admin();
		int id = 416;
		Mockito.when(adminController.getAdminById(id)).thenReturn(admin);
		assertEquals(admin, adminController.getAdminById(id));
		/*assertEquals(admin.getAdminName(),adminController.insertAdmin(admin).getAdminName());
		assertEquals(admin.getAdminPassword(),adminController.insertAdmin(admin).getAdminPassword());*/
	}
	@Test
	void testupdateAdmin() {
		 Admin admin=new Admin("Hamsi","Hamsi852");
		 Mockito.when(adminController.updateAdmin(admin)).thenReturn(admin);
	     assertEquals(admin,adminController.updateAdmin(admin));
		 
	}
	@Test
	void testdeleteAdmin() {
		 boolean b= true;
		 int id=45;
		 Mockito.when(adminController.deleteAdmin(id)).thenReturn(b);
	     assertEquals(b,adminController.deleteAdmin(id));
	}
	@Test
	void testGetAll()
	{
		 List<Admin> admin=new ArrayList<Admin>();
		 Mockito.when(adminController.getAllAdmin()).thenReturn(admin);
		 assertEquals(admin, adminController.getAllAdmin());
			
	}

}
