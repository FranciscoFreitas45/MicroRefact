package com.poseidon.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TransactionServiceController {

 private TransactionService transactionservice;


@GetMapping
("/fetchTransactionFromTag")
public TransactionReportVO fetchTransactionFromTag(@RequestParam(name = "tagNo") String tagNo){
  return transactionservice.fetchTransactionFromTag(tagNo);
}


@GetMapping
("/listAllTransactions")
public List<TransactionVO> listAllTransactions(){
  return transactionservice.listAllTransactions();
}


@GetMapping
("/allTagNumbers")
public List<String> allTagNumbers(){
  return transactionservice.allTagNumbers();
}


}