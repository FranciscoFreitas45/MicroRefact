package com.cg.hbm.service;
 import java.util.List;
import com.cg.hbm.entites.Payments;
import com.cg.hbm.exceptions.PaymentNotFoundException;
public interface IPaymentService {


public Payments showPayments(int payment_id)
;

public Payments addPayment(Payments payments)
;

public List<Payments> showAllPayments()
;

}