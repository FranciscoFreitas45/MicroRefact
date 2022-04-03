package com.project.stockexchangeappbackend.Interface;
public interface TransactionService {

   public Page<Transaction> getOwnedTransactions(Pageable pageable,Specification<Transaction> specification,boolean isSeller,boolean isBuyer);
   public Page<Transaction> getUserTransactions(Pageable pageable,Specification<Transaction> specification,Long userId,boolean isSeller,boolean isBuyer);
}