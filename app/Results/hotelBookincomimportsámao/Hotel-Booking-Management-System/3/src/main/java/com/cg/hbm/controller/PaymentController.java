/*
 * package com.cg.hbm.controller; import java.util.List;

 * 
 * 
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * 
 * import com.cg.hbm.entites.Payments; import
 * com.cg.hbm.exceptions.PaymentNotFoundException; import
 * com.cg.hbm.service.IPaymentService;
 * 
 * @RestController
 * 
 * @RequestMapping("/payment") public class PaymentController {
 * 
 * @Autowired IPaymentService iService;
 * 
 * 
 * @PostMapping public ResponseEntity<Payments> addPayment(@RequestBody Payments
 * payments) throws PaymentNotFoundException { Payments
 * resultPayments=iService.addPayment(payments); return new
 * ResponseEntity<Payments>(resultPayments,HttpStatus.OK) ; }
 * 
 * 
 * @GetMapping
 * 
 * public ResponseEntity<List<Payments>> showAllPayments() throws
 * PaymentNotFoundException{ List<Payments>
 * resultPayments=iService.showAllPayments(); return new
 * ResponseEntity<List<Payments>>(resultPayments, HttpStatus.OK); }
 * 
 * 
 * @GetMapping("/{id}") public
 * ResponseEntity<Payments>showPayments(@PathVariable int id) throws
 * PaymentNotFoundException { Payments p=iService.showPayments(id); if(p!=null)
 * { return new ResponseEntity<Payments>(p,HttpStatus.OK); } else { return new
 * ResponseEntity<Payments>(HttpStatus.NOT_FOUND); }
 * 
 * } }
 * 
 */
package com.cg.hbm.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.hbm.entites.Payments;
import com.cg.hbm.exceptions.PaymentNotFoundException;
import com.cg.hbm.service.IPaymentService;
/**
 * 
 * @author Keerthi
 *
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	IPaymentService iService;
	/**************************************************************************************************
	 * Method                                      addPayments
	 * Description                                 To add the payments to the database
	 * @param payments                             payments to be added to the database
	 * @param RequestBody                          It maps the HttpRequest to handler methods of MVC and RestController
	 * @returns Payments                           returns bookingdetails after adding the payments to database 
	 * @throws PaymentNotFoundException            It is raised when payments already exists
	 **************************************************************************************************/

	@PostMapping("/add")
	public ResponseEntity<Payments> addPayment(@RequestBody Payments payment) throws PaymentNotFoundException  {
		Payments resultPayments=iService.addPayment(payment);
		return new ResponseEntity<Payments>(resultPayments,HttpStatus.OK) ;
	}
	/***********************************************************************************************
	 * Method                                     ShowAllPayments
	 * Description                                To get all the payments from the database
	 * @returns List<Payments>                    returns payments after fetching from the database 
	 * @throws PaymentNotFoundException           It is raised when payments does not found
	 ************************************************************************************************/
	
	@GetMapping("/all")

	public ResponseEntity<List<Payments>> showAllPayments() throws PaymentNotFoundException{
		List<Payments> resultPayments=iService.showAllPayments();
		return new ResponseEntity<List<Payments>>(resultPayments, HttpStatus.OK);
	}
	/************************************************************************************************
	 * Method                                showPayment 
	 * Description                           To find the payments from the database using id
	 * @param payments                       To fetch the payments from the database
	 * @returns Payments                     returns payments with id after fetching from the database
	 * @throws PaymentNotFoundException      It is raised when payments does not exists
	 *************************************************************************************************/
	
	@GetMapping("/{payment_id}")
	public ResponseEntity<Payments>showPayments(@PathVariable int payment_id) throws PaymentNotFoundException {
		Payments p=iService.showPayments(payment_id);
		if(p!=null) {
			return new ResponseEntity<Payments>(p,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Payments>(HttpStatus.NOT_FOUND);
		}
		
	}
}

