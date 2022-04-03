package com.cg.hbm.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.hbm.entites.Transactions;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface ITransactionRepository extends JpaRepository<Transactions, Integer>{


@Transactional
@Modifying
@Query(value = "update transactions t set t.transaction_id = ?1 where t.transaction_id = ?1", nativeQuery = true)
public void setTransactions(int transaction_id,Transactions transactions);

@Query(value = "Select * from transactions t  where t.transaction_id = ?1", nativeQuery = true)
public Transactions getTransactions(int transaction_id);

}