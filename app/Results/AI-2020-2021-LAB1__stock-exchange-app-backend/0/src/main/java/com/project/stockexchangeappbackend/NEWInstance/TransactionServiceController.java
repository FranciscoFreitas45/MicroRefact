package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TransactionServiceController {

 private TransactionService transactionservice;


@GetMapping
("/getTransactionsByStockIdForPricing")
public List<Transaction> getTransactionsByStockIdForPricing(@RequestParam(name = "stockId") Long stockId,@RequestParam(name = "amount") Integer amount){
  return transactionservice.getTransactionsByStockIdForPricing(stockId,amount);
}


@GetMapping
("/getOwnedTransactions")
public Page<Transaction> getOwnedTransactions(@RequestParam(name = "pageable") Pageable pageable,@RequestParam(name = "specification") Specification<Transaction> specification,@RequestParam(name = "isSeller") boolean isSeller,@RequestParam(name = "isBuyer") boolean isBuyer){
  return transactionservice.getOwnedTransactions(pageable,specification,isSeller,isBuyer);
}


@GetMapping
("/getUserTransactions")
public Page<Transaction> getUserTransactions(@RequestParam(name = "pageable") Pageable pageable,@RequestParam(name = "specification") Specification<Transaction> specification,@RequestParam(name = "userId") Long userId,@RequestParam(name = "isSeller") boolean isSeller,@RequestParam(name = "isBuyer") boolean isBuyer){
  return transactionservice.getUserTransactions(pageable,specification,userId,isSeller,isBuyer);
}


@GetMapping
("/getTransactionsByOrder")
public Page<Transaction> getTransactionsByOrder(@RequestParam(name = "pageable") Pageable pageable,@RequestParam(name = "specification") Specification<Transaction> specification,@RequestParam(name = "orderId") Long orderId){
  return transactionservice.getTransactionsByOrder(pageable,specification,orderId);
}


@GetMapping
("/findTransactionById")
public Transaction findTransactionById(@RequestParam(name = "id") Long id){
  return transactionservice.findTransactionById(id);
}


@GetMapping
("/findAllTransactions")
public Page<Transaction> findAllTransactions(@RequestParam(name = "pageable") Pageable pageable,@RequestParam(name = "specification") Specification<Transaction> specification){
  return transactionservice.findAllTransactions(pageable,specification);
}


}