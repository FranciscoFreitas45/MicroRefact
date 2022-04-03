package com.poseidon.Interface;
public interface TransactionDAO {

   public List<TransactionVO> listAllTransactions();
   public List<TransactionVO> listTodaysTransactions();
   public String saveTransaction(TransactionVO currentTransaction);
   public List<TransactionVO> searchTransactions(TransactionVO searchTransaction);
   public TransactionVO fetchTransactionFromId(Long id);
   public TransactionReportVO fetchTransactionFromTag(String tagNo);
   public void updateTransaction(TransactionVO currentTransaction);
   public void deleteTransaction(Long id);
   public void updateTransactionStatus(Long id,String status);
   public List<String> allTagNumbers();
}