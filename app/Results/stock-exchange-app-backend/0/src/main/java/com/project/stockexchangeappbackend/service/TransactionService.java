package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.entity.Order;
import com.project.stockexchangeappbackend.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;
import java.util.List;
public interface TransactionService {


public Page<Transaction> getOwnedTransactions(Pageable pageable,Specification<Transaction> specification,boolean isSeller,boolean isBuyer)
;

public Transaction findTransactionById(Long id)
;

public Page<Transaction> getUserTransactions(Pageable pageable,Specification<Transaction> specification,Long userId,boolean isSeller,boolean isBuyer)
;

public void makeTransaction(Order buyingOrder,Order sellingOrder,int amount,BigDecimal pricePerUnit)
;

public Page<Transaction> findAllTransactions(Pageable pageable,Specification<Transaction> specification)
;

public Page<Transaction> getTransactionsByOrder(Pageable pageable,Specification<Transaction> specification,Long orderId)
;

public List<Transaction> getTransactionsByStockIdForPricing(Long stockId,Integer amount)
;

}