package com.sprint2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint2.Exceptions.InvalidOperation;
import com.sprint2.controller.LandController;
import com.sprint2.model.Land;
import com.sprint2.service.LandService;

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
class LandControllerTest {
	@Mock
	LandService landservice;
	
	@InjectMocks
	LandController landController;

	@Test
	public void update()throws InvalidOperation
	{
		Land land=new Land("eeg","5","ddgg");
		Mockito.when(landController.updateLand(land)).thenReturn(land);
		assertEquals(land,landController.updateLand(land));

	}
	@Test
    public void getall() {
        List<Land> land=new ArrayList<Land>();
        Mockito.when(landController.getAllLand()).thenReturn(land);
        assertEquals(land,landController.getAllLand());

    }
	@Test
	public void addLand() 
	{
		Land p1=new Land("dfgh","5","sfdghyujy");
		Mockito.when(landController.addLand(p1)).thenReturn(p1);
	    assertEquals(p1,landController.addLand(p1));
	}
	@Test
    public void get() {
		Land land=new Land();
		
        Mockito.when(landController.getLandBylandId(26)).thenReturn(land);
        assertEquals(land,landController.getLandBylandId(26));

    }
	@Test
		public void testdelete() {
	 	boolean str1=true;;
	 	int id=29;
	 	Mockito.when(landController.deleteLandbylandId(id)).thenReturn(str1);
	 	assertEquals(str1,landController.deleteLandbylandId(id));
}

}
