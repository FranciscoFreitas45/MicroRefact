package com.cg.hbm.DTO;

public class Transactions {

 private  int transaction_id;

 private  double amount;

public Transactions() {
}public Transactions(double amount) {
    super();
    this.amount = amount;
}
public int getTransaction_id(){
    return transaction_id;
}


public double getAmount(){
    return amount;
}
public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}



}