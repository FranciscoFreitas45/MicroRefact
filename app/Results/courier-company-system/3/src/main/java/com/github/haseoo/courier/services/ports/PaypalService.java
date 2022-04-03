package com.github.haseoo.courier.services.ports;
 import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
public interface PaypalService {


public Payment executePayment(String paymentId,String payerId)
;

public String createPayment(Long id)
;

}