package com.easyshopping.service;
 import javax.servlet.http.HttpServletRequest;
import com.easyshopping.entity.Payment;
public interface PaymentService extends BaseService<Payment, Long>{


public Payment findBySn(String sn)
;

public void handle(Payment payment,HttpServletRequest request)
;

}