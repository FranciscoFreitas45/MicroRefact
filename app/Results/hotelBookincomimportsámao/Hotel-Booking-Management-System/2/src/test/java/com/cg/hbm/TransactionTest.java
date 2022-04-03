package com.cg.hbm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.hbm.entites.Transactions;

@SpringBootTest
public class TransactionTest extends AbstractTest {

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
	public void ShowAllTransactions() throws Exception {
		String uri = "/transaction/all";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Transactions transaction[] = super.mapFromJson(content, Transactions[].class);
		assertEquals(12000, transaction[0].getAmount());
	}

	@Test
	public void addTransaction() throws Exception {
		Transactions transaction = new Transactions();
		transaction.setAmount(12000);
		;
		String uri = "/transaction/add";
		String inputJson = super.mapToJson(transaction);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Transactions t = super.mapFromJson(content, Transactions.class);
		assertEquals(12000, t.getAmount());
	}

}
