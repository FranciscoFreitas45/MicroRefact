package com.cg.hbm.Request;
import com.cg.hbm.DTO.Transactions;
public interface TransactionsRequest {

   public void setTransactions(Transactions transactions,int transaction_id);
   public Transactions getTransactions(int transaction_id);
}