package com.poseidon.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TransactionDAOController {

 private TransactionDAO transactiondao;


@GetMapping
("/listAllTransactions")
public List<TransactionVO> listAllTransactions(){
  return transactiondao.listAllTransactions();
}


@GetMapping
("/listTodaysTransactions")
public List<TransactionVO> listTodaysTransactions(){
  return transactiondao.listTodaysTransactions();
}


@GetMapping
("/saveTransaction")
public String saveTransaction(@RequestParam(name = "currentTransaction") TransactionVO currentTransaction){
  return transactiondao.saveTransaction(currentTransaction);
}


@GetMapping
("/searchTransactions")
public List<TransactionVO> searchTransactions(@RequestParam(name = "searchTransaction") TransactionVO searchTransaction){
  return transactiondao.searchTransactions(searchTransaction);
}


@GetMapping
("/fetchTransactionFromId")
public TransactionVO fetchTransactionFromId(@RequestParam(name = "id") Long id){
  return transactiondao.fetchTransactionFromId(id);
}


@GetMapping
("/fetchTransactionFromTag")
public TransactionReportVO fetchTransactionFromTag(@RequestParam(name = "tagNo") String tagNo){
  return transactiondao.fetchTransactionFromTag(tagNo);
}


@PutMapping
("/updateTransaction")
public void updateTransaction(@RequestParam(name = "currentTransaction") TransactionVO currentTransaction){
transactiondao.updateTransaction(currentTransaction);
}


@PutMapping
("/deleteTransaction")
public void deleteTransaction(@RequestParam(name = "id") Long id){
transactiondao.deleteTransaction(id);
}


@PutMapping
("/updateTransactionStatus")
public void updateTransactionStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") String status){
transactiondao.updateTransactionStatus(id,status);
}


@GetMapping
("/allTagNumbers")
public List<String> allTagNumbers(){
  return transactiondao.allTagNumbers();
}


}