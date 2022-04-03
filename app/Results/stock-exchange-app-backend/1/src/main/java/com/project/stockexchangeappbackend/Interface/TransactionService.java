package com.project.stockexchangeappbackend.Interface;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.Transaction;

public interface TransactionService {

   public List<Transaction> getTransactionsByStockIdForPricing(Long stockId,Integer amount);
}