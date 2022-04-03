package com.poseidon.transaction.service;
 import com.poseidon.transaction.dao.TransactionDAO;
import com.poseidon.transaction.domain.TransactionReportVO;
import com.poseidon.transaction.domain.TransactionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.poseidon.Interface.TransactionDAO;
@Service
@SuppressWarnings("unused")
public class TransactionService {

 private  Logger LOG;

 private  String EXCEPTION_TYPE_IN_SERVICE_IMPL;

 private  TransactionDAO transactionDAO;

public TransactionService(final TransactionDAO transactionDAO) {
    this.transactionDAO = transactionDAO;
}
public List<TransactionVO> searchTransactions(TransactionVO searchTransaction){
    return transactionDAO.searchTransactions(searchTransaction);
}


public List<TransactionVO> listTodaysTransactions(){
    return transactionDAO.listTodaysTransactions();
}


public void updateTransaction(TransactionVO currentTransaction){
    transactionDAO.updateTransaction(currentTransaction);
}


public List<TransactionVO> listAllTransactions(){
    return transactionDAO.listAllTransactions();
}


public TransactionReportVO fetchTransactionFromTag(String tagNo){
    return transactionDAO.fetchTransactionFromTag(tagNo);
}


public TransactionVO fetchTransactionFromId(Long id){
    return transactionDAO.fetchTransactionFromId(id);
}


public void deleteTransaction(Long id){
    transactionDAO.deleteTransaction(id);
}


public void updateTransactionStatus(Long id,String status){
    transactionDAO.updateTransactionStatus(id, status);
}


public String saveTransaction(TransactionVO currentTransaction){
    return transactionDAO.saveTransaction(currentTransaction);
}


public List<String> allTagNumbers(){
    return transactionDAO.allTagNumbers();
}


}