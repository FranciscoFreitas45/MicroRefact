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
import com.cg.hbm.service.ITransactionService;
@RestController
@RequestMapping("/transaction")
public class TransactionController {

@Autowired
 private ITransactionService iService;


@PostMapping("/add")
public ResponseEntity<Transactions> addTransaction(Transactions transaction){
    Transactions resultTransaction = iService.addTransaction(transaction);
    return new ResponseEntity<Transactions>(resultTransaction, HttpStatus.OK);
}


@GetMapping("/all")
public ResponseEntity<List<Transactions>> showAllTransactions(){
    List<Transactions> resultTransaction = iService.showAllTransactions();
    return new ResponseEntity<List<Transactions>>(resultTransaction, HttpStatus.OK);
}


}