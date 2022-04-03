package com.cg.hbm.service;

import java.util.List;



import com.cg.hbm.entites.Transactions;
/***************************************************************************************************************
 *@author          	KV Prasad
 *Description      	It is a ITransactionService Interface and provides methods for the Implementation class.  
 *Version          	1.0
 *Created Date    	31-MAR-2021
 **************************************************************************************************************/


public interface ITransactionService {
	public Transactions addTransaction(Transactions transaction)throws Exception;
	public List<Transactions> showAllTransactions()throws Exception;

	
}
