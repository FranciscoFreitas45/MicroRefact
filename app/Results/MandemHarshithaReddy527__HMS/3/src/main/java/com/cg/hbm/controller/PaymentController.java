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
import com.cg.hbm.exceptions.BookingDetailsNotFoundException;
import com.cg.hbm.exceptions.HotelNotFoundException;
import com.cg.hbm.exceptions.PaymentNotFoundException;
import com.cg.hbm.service.IPaymentService;
@RestController
@RequestMapping("/payment")
public class PaymentController {

@Autowired
 private IPaymentService iService;


@GetMapping("/{payment_id}")
public ResponseEntity<Payments> showPayments(int payment_id){
    Payments p = iService.showPayments(payment_id);
    if (p != null) {
        return new ResponseEntity<Payments>(p, HttpStatus.OK);
    } else {
        return new ResponseEntity<Payments>(HttpStatus.NOT_FOUND);
    }
}


@PostMapping("/add")
public ResponseEntity<Payments> addPayment(Payments payment){
    Payments resultPayments = iService.addPayment(payment);
    return new ResponseEntity<Payments>(resultPayments, HttpStatus.OK);
}


@GetMapping("/all")
public ResponseEntity<List<Payments>> showAllPayments(){
    List<Payments> resultPayments = iService.showAllPayments();
    return new ResponseEntity<List<Payments>>(resultPayments, HttpStatus.OK);
}


}