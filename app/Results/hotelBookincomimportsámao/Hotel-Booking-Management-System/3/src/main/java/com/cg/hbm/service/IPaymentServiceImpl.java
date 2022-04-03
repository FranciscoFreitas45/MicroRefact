
package com.cg.hbm.service;

/*import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hbm.entites.Hotel;
import com.cg.hbm.entites.Payments;
import com.cg.hbm.exceptions.*;
import com.cg.hbm.dao.IHotelRepository;
import com.cg.hbm.dao.IPaymentRepository;

@Service
public class IPaymentServiceImpl implements IPaymentService{
	@Autowired
	IPaymentRepository pDao;

	@Override
	public Payments addPayment(Payments payment) {
		return pDao.save(payment);
	}

	


	@Override
	public List<Payments> showAllPayments() {
		return pDao.findAll();
	}

	@Override
	public Payments showPayments(int payment_id) {
		return pDao.findById(payment_id).get();
	}

}*/
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.hbm.dao.IPaymentRepository;
import com.cg.hbm.entites.Payments;
import com.cg.hbm.exceptions.PaymentNotFoundException;

/**************************************************************************************
* @author            Keerthi
* Description        It is a service class that provides the services to add payments, 
* 					 get payments, show all payments.
* 
* Version 1.0        Created Date 24-March-2021
***************************************************************************************/

@Service
@Transactional
public class IPaymentServiceImpl implements IPaymentService {
	@Autowired
	IPaymentRepository repository;

	/***************************************************************************************************************
	 * Method                                addPayment 
	 * Description                           To add the PaymentDetails to the database
	 * @param payments                       PaymentDetails to be added to the database
	 * @returns Payments                     returns Payments after adding the PaymentDetails with the payment_id to the database 
	 * @throws PaymentNotFoundException      It is raised when payment_id already exists
	 * CreatedBy                             Keerthi 
	 * Created Date                          23-MAR-2021
	 ****************************************************************************************************************/
	
	
	@Override
	public Payments addPayment(Payments payments) throws PaymentNotFoundException{
		if(repository.existsById(payments.getPayment_id())){
			throw new PaymentNotFoundException("Payment with given Id already exists.");
		}
		else
		repository.saveAndFlush(payments);
		return payments;
	}
	
	/******************************************************************************************
	 * Method                           showAllPayments
	 * Description                      To get all the paymentdetails from the database
	 * @param hotel                     To fetch all the paymentdetails from the database
	 * @returns Hotel                   returns paymentdetails after fetching from the database 
	 * @throws HotelNotFoundException   It is raised when payment is not found
	 * CreatedBy                        Keerthi
	 * Created Date                     23-MAR-2021
	 *******************************************************************************************/
	
	@Override
	public List<Payments> showAllPayments() throws PaymentNotFoundException {
		List<Payments> p = repository.findAll();
		if (p.isEmpty()) {
			throw new PaymentNotFoundException("Payment not found");
		}
		return p;
	}
	
	
	/*********************************************************************************************
	 * Method                           showPayments 
	 * Description                      To get the paymentdetails from the database
	 * @param hotel                     To fetch the paymentdetails from the database
	 * @returns Hotel                   returns paymentdetails with payment_id after fetching the database
	 * @throws HotelNotFoundException   It is raised when payment does not exists
	 * CreatedBy                        keerthi
	 * Created Date                     23-MAR-2021
	 ***********************************************************************************************/

	@Override
	public Payments showPayments(int payment_id) throws PaymentNotFoundException{
		if(repository.findById(payment_id).isPresent()) {
			return repository.findById(payment_id).get();
		}
		else {
			throw new PaymentNotFoundException("Payment with given Id not found.");
		}
		
	}
}

 