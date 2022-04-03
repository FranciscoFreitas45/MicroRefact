package com.sprint2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sprint2.controller.ContractController;
import com.sprint2.model.Contract;
import com.sprint2.service.ContractService;


@ExtendWith(MockitoExtension.class)
public class ContractTest 
{
	@Mock
	ContractService contractService;
	
	@InjectMocks
	ContractController contractController;
	
	@Test
	public void update() 
	{
		Contract contract=new Contract("hyd","8/10/2011","6");
		Mockito.when(contractController.updateContract(contract)).thenReturn(contract);
		assertEquals(contract,contractController.updateContract(contract));

	}
	@Test
    public void getall() {
        List<Contract> contract=new ArrayList<Contract>();
        Mockito.when(contractController.getAllContract()).thenReturn(contract);
        assertEquals(contract,contractController.getAllContract());

    }
	@Test
	public void addContracts() 
	{
		Contract p1=new Contract("mumbai","14/12/2019","7");
		Mockito.when(contractController.addContract(p1)).thenReturn(p1);
	    assertEquals(p1,contractController.addContract(p1));
	}
	@Test
    public void get() {
		Contract contract=new Contract();
        Mockito.when(contractController.getContractBycontractNumber(39)).thenReturn(contract);
        assertEquals(contract,contractController.getContractBycontractNumber(39));

    }
	@Test
	 public void testdelete() {
		 boolean str1=true;
		 int id=26;
		 Mockito.when(contractController.deleteContractbycontractNumber(id)).thenReturn(str1);
		    assertEquals(str1,contractController.deleteContractbycontractNumber(id));
	 }


}