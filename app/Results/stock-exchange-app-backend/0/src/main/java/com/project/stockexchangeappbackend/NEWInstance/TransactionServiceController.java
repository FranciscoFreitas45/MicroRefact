package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
 import com.project.stockexchangeappbackend.service.*;
 import com.project.stockexchangeappbackend.entity.*;
 import com.project.stockexchangeappbackend.DTO.*;
 import java.util.*;

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


}