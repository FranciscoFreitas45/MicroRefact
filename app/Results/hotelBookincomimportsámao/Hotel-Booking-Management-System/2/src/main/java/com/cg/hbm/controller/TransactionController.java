package com.cg.hbm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.hbm.entites.Transactions;
import com.cg.hbm.exceptions.TransactionNotFoundException;
import com.cg.hbm.exceptions.UserNotFoundException;
import com.cg.hbm.service.ITransactionService;
/****************************
 * @author KV Prasad
 * Description : It is a Controller class of Transaction Module. 
 * Version 1.0
 * Created Date 24-March-2021
 ****************************/

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	ITransactionService iService;
	
	
	/****************************
	 * Method                                : addTransation
	 * Description                           : To add new transaction to the database
	 * @param transaction                    - transaction to be added to the database
	 * @param RequestBody                    - It maps the HttpRequest body to a transfer or domain object,
                                               enabling automatic deserialization of the inbound HttpRequest 
                                               body onto a Java object.
	 * @returns Transaction                  - returns transaction after adding the transaction to database 
	 * @throws TransactionNotFoundException  - It is raised when transaction already exists
	 ****************************/
	
	@PostMapping("/add")
	public ResponseEntity<Transactions> addTransaction(@RequestBody Transactions transaction) throws Exception  {
		Transactions resultTransaction=iService.addTransaction(transaction);
		return new ResponseEntity<Transactions>(resultTransaction,HttpStatus.OK) ;
	}
	
	
	/****************************
	 * Method                                : showAllTransactions
	 * Description                           : To get all the transactions from the database
	 * @returns List<Transaction>            - returns transactions after fetching the database 
	 * @throws TransactionNotFoundException  - It is raised when transaction is not found
	 ****************************/
	@GetMapping("/all")
	public ResponseEntity<List<Transactions>> showAllTransactions() throws Exception{
		List<Transactions> resultTransaction=iService.showAllTransactions();
		return new ResponseEntity<List<Transactions>>(resultTransaction, HttpStatus.OK);
	}
}

