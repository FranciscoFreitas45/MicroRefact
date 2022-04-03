package com.cg.hbm.service;

import java.util.List;




import com.cg.hbm.entites.Payments;
import com.cg.hbm.exceptions.PaymentNotFoundException;
/***************************************************************************************************************
 *@author          	Keerthi
 *Description      	It is a IPaymentService Interface and provides methods for the Implementation class.  
 *Version          	1.0
 *Created Date    	31-MAR-2021
 **************************************************************************************************************/

public interface IPaymentService {
	
	public Payments addPayment(Payments payments) throws PaymentNotFoundException;
	public List<Payments> showAllPayments() throws PaymentNotFoundException;
	public Payments showPayments(int payment_id) throws PaymentNotFoundException;
}
