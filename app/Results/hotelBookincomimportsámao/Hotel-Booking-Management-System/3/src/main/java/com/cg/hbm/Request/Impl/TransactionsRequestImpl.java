package com.cg.hbm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.DTO.Transactions;
import com.cg.hbm.Request.TransactionsRequest;
public class TransactionsRequestImpl implements TransactionsRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTransactions(Transactions transactions,int transaction_id){
 restTemplate.put("http://127.0.0.1:8082/Payments/{id}/Transactions/setTransactions",transactions,transaction_id);
 return ;
}


public Transactions getTransactions(int transaction_id){
 Transactions aux = restTemplate.getForObject("http://127.0.0.1:8082/Payments/{id}/Transactions/getTransactions",Transactions.class,transaction_id);
return aux;
}


}