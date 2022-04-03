package com.poseidon.Interface;
public interface TransactionService {

   public TransactionReportVO fetchTransactionFromTag(String tagNo);
   public List<TransactionVO> listAllTransactions();
   public List<String> allTagNumbers();
}