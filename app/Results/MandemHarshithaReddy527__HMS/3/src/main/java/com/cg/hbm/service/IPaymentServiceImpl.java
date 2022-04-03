package com.cg.hbm.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.hbm.dao.IPaymentRepository;
import com.cg.hbm.entites.Payments;
import com.cg.hbm.exceptions.HotelNotFoundException;
import com.cg.hbm.exceptions.PaymentNotFoundException;
@Service
@Transactional
public class IPaymentServiceImpl implements IPaymentService{

@Autowired
 private IPaymentRepository repository;


@Override
public Payments showPayments(int payment_id){
    if (repository.findById(payment_id).isPresent()) {
        return repository.findById(payment_id).get();
    } else {
        throw new PaymentNotFoundException("Payment with given Id not found.");
    }
}


@Override
public Payments addPayment(Payments payments){
    if (repository.existsById(payments.getPayment_id())) {
        throw new PaymentNotFoundException("Payment with given Id already exists.");
    } else
        repository.saveAndFlush(payments);
    return payments;
}


@Override
public List<Payments> showAllPayments(){
    List<Payments> p = repository.findAll();
    if (p.isEmpty()) {
        throw new PaymentNotFoundException("Payment not found");
    }
    return p;
}


}