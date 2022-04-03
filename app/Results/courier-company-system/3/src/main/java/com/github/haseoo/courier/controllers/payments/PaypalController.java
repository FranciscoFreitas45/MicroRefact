package com.github.haseoo.courier.controllers.payments;
 import com.github.haseoo.courier.services.ports.ChangeParcelStateService;
import com.github.haseoo.courier.services.ports.PaypalService;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.github.haseoo.courier.utilities.Constants.FAILURE_REDIRECT;
import com.github.haseoo.courier.utilities.Constants.SUCCESS_REDIRECT;
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaypalController {

 private  PaypalService paypalService;

 private  ChangeParcelStateService changeParcelStateService;


@GetMapping("/paypal/success")
public RedirectView handlePaypalSuccess(String paymentId,String payerId,RedirectAttributes attributes){
    changeParcelStateService.changeParcelState(paypalService.executePayment(paymentId, payerId));
    attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
    return new RedirectView(SUCCESS_REDIRECT);
}


@PostMapping("/paypal")
public String payment(Long id){
    return paypalService.createPayment(id);
}


@GetMapping("/paypal/cancel")
public RedirectView handlePaypalCancel(String token,RedirectAttributes attributes){
    attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
    return new RedirectView(FAILURE_REDIRECT);
}


}