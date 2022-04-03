package com.cg.hbm.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class Transactions {

 private  int transaction_id;

 private  double amount;

public Transactions() {
}/**
 * @param amount
 */
public Transactions(double amount) {
    super();
    this.amount = amount;
}
public int getTransaction_id(){
    return transaction_id;
}


public double getAmount(){
    return amount;
}


}