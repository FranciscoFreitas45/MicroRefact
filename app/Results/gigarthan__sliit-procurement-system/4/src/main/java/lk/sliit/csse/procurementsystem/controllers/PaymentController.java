package lk.sliit.csse.procurementsystem.controllers;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import lk.sliit.csse.procurementsystem.models.Payment;
import lk.sliit.csse.procurementsystem.repositories.PaymentRepository;
public class PaymentController {

 private  Payment payment;

@Autowired
 private  PaymentRepository paymentRepository;


public List<Payment> getPayment(){
    return paymentRepository.findAll();
}


public void addPayment(){
    paymentRepository.save(payment);
    this.payment = new Payment();
}


}