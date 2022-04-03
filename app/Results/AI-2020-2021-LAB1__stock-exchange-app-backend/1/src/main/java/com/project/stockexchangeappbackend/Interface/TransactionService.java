package com.project.stockexchangeappbackend.Interface;
public interface TransactionService {

   public List<Transaction> getTransactionsByStockIdForPricing(Long stockId,Integer amount);
}