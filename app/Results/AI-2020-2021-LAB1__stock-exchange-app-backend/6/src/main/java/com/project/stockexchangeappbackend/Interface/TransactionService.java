package com.project.stockexchangeappbackend.Interface;
public interface TransactionService {

   public Transaction findTransactionById(Long id);
   public Page<Transaction> findAllTransactions(Pageable pageable,Specification<Transaction> specification);
}