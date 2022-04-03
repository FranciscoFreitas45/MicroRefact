package com.cg.hbm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.entites.Transactions;
@RestController
@CrossOrigin
public class TransactionsPaymentsController {

@Autowired
 private TransactionsPaymentsService transactionspaymentsservice;


@PutMapping
("/Payments/{id}/Transactions/setTransactions")
public void setTransactions(@PathVariable(name="id") int transaction_id,@RequestBody Transactions transactions){
transactionspaymentsservice.setTransactions(transaction_id,transactions);
}


@GetMapping
("/Payments/{id}/Transactions/getTransactions")
public Transactions getTransactions(@PathVariable(name="id") int transaction_id){
return transactionspaymentsservice.getTransactions(transaction_id);
}


}