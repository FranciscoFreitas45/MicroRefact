package com.easyshopping.dao;
 import com.easyshopping.entity.Payment;
public interface PaymentDao extends BaseDao<Payment, Long>{


public Payment findBySn(String sn)
;

}