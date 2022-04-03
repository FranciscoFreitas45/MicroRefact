package com.cg.hbm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cg.hbm.dao.ITransactionRepository;
import com.cg.hbm.entites.Transactions;
import com.cg.hbm.exceptions.TransactionNotFoundException;

/****************************
 * @author KV Prasad
 * Description : It is a service class that provides the services to add a user and view all transactions.
 * Version 1.0
 * Created Date 24-March-2021
 ****************************/


@Service
@Transactional
public class ITransactionServiceImpl implements ITransactionService{
	@Autowired
	ITransactionRepository tDao;
	@Override
	public  Transactions addTransaction(Transactions transaction) throws Exception{
		Optional<Transactions> t = tDao.findById(transaction.getTransaction_id());
		if (t.isEmpty()) {
			return tDao.saveAndFlush(transaction);
		} else {
			throw new Exception("Transaction already exists");
		}
	}
	@Override
	public List<Transactions> showAllTransactions() throws Exception {
		// TODO Auto-generated method stub
		List<Transactions> h = tDao.findAll();
		if (h.isEmpty()) {
			throw new Exception("Transactions not found");
		}
		return h;
	}
	 
}
