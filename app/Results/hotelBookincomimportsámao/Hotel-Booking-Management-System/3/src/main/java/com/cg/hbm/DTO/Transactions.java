package com.cg.hbm.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class Transactions {

 private  int transaction_id;

 private  double amount;


public int getTransaction_id(){
    return transaction_id;
}


public double getAmount(){
    return amount;
}


	public Transactions() {

	}

	public Transactions(double amount) {
		super();
		this.amount = amount;
	}
public void setId(int id){
    this.transaction_id = id;
}

}