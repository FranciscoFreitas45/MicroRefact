package com.cg.hbm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.dao.ITransactionRepository;
import com.cg.hbm.entites.Transactions;
@Service
public class TransactionsPaymentsService {

@Autowired
 private ITransactionRepository itransactionrepository;


public void setTransactions(int transaction_id,Transactions transactions){
itransactionrepository.setTransactions(transaction_id,transactions);
}


public Transactions getTransactions(int transaction_id){
return itransactionrepository.getTransactions(transaction_id);
}


}