package com.cg.hbm.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.hbm.entites.Transactions;
public interface ITransactionRepository extends JpaRepository<Transactions, Integer>{


public void setTransactions(int transaction_id,Transactions transactions);

public Transactions getTransactions(int transaction_id);

}