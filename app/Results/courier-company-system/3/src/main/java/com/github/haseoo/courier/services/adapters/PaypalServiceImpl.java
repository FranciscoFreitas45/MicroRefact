package com.github.haseoo.courier.services.adapters;
 import com.github.haseoo.courier.configuration.PaypalConfig;
import com.github.haseoo.courier.exceptions.serviceexceptions.parcelsexceptions.IllegalParcelState;
import com.github.haseoo.courier.exceptions.serviceexceptions.parcelsexceptions.ParcelNotFound;
import com.github.haseoo.courier.models.ParcelModel;
import com.github.haseoo.courier.models.ReceiverInfoModel;
import com.github.haseoo.courier.repositories.ports.ParcelRepository;
import com.github.haseoo.courier.services.ports.PaypalService;
import com.github.haseoo.courier.utilities.Constants;
import com.paypal.api.payments;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PaypalServiceImpl implements PaypalService{

 private  PaypalConfig paypalConfig;

 private  ParcelRepository parcelRepository;


@Override
public Payment executePayment(String paymentId,String payerId){
    PaymentExecution paymentExecution = new PaymentExecution();
    paymentExecution.setPayerId(payerId);
    Payment payment = new Payment().setId(paymentId);
    return payment.execute(paypalConfig.getApiContext(), paymentExecution);
}


public Payer getPayerInformation(ReceiverInfoModel receiverInfoModel){
    Payer payer = new Payer();
    payer.setPaymentMethod("paypal");
    PayerInfo payerInfo = new PayerInfo();
    payerInfo.setEmail(receiverInfoModel.getEmailAddress());
    payer.setPayerInfo(payerInfo);
    return payer;
}


public Payment getPaymentDetails(String paymentId){
    APIContext apiContext = paypalConfig.getApiContext();
    return Payment.get(apiContext, paymentId);
}


public RedirectUrls getRedirectURLs(){
    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl(Constants.CANCEL_URL);
    redirectUrls.setReturnUrl(Constants.SUCCESS_URL);
    return redirectUrls;
}


public List<Transaction> getTransactionInformation(ParcelModel parcelModel){
    Details details = new Details();
    if (parcelModel.getPriority())
        details.setShipping(parcelModel.getParcelType().getPrice().multiply(BigDecimal.valueOf(1.1)).setScale(2, RoundingMode.CEILING).toString());
    else
        details.setShipping(parcelModel.getParcelType().getPrice().toString());
    details.setSubtotal(parcelModel.getParcelFee().toString());
    details.setTax("0.00");
    Amount amount = new Amount();
    amount.setCurrency("PLN");
    amount.setTotal(countTotalPrice(details.getShipping(), details.getSubtotal()));
    amount.setDetails(details);
    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    transaction.setDescription("Fee for posting a shipment");
    ItemList itemList = new ItemList();
    List<Item> items = new ArrayList<>();
    Item item = new Item().setCurrency("PLN").setName("Parcel " + parcelModel.getId()).setPrice(parcelModel.getParcelFee().toString()).setTax("0").setQuantity("1").setCategory("PHYSICAL");
    items.add(item);
    itemList.setItems(items);
    transaction.setItemList(itemList);
    List<Transaction> listTransaction = new ArrayList<>();
    listTransaction.add(transaction);
    return listTransaction;
}


@Override
public String createPayment(Long id){
    ParcelModel parcelModel = parcelRepository.getById(id).orElseThrow(() -> new ParcelNotFound(id));
    if (parcelModel.getPaid())
        throw new IllegalParcelState();
    Payer payer = getPayerInformation(parcelModel.getReceiverContactData());
    RedirectUrls redirectUrls = getRedirectURLs();
    List<Transaction> listTransaction = getTransactionInformation(parcelModel);
    Payment requestPayment = new Payment();
    requestPayment.setTransactions(listTransaction);
    requestPayment.setRedirectUrls(redirectUrls);
    requestPayment.setPayer(payer);
    requestPayment.setIntent("sale");
    APIContext apiContext = paypalConfig.getApiContext();
    Payment approvedPayment = requestPayment.create(apiContext);
    return getApprovalLink(approvedPayment);
}


public String countTotalPrice(String shipping,String subtotal){
    BigDecimal shippingBigDecimal = new BigDecimal(shipping);
    BigDecimal subtotalBigDecimal = new BigDecimal(subtotal);
    return shippingBigDecimal.add(subtotalBigDecimal).toString();
}


public String getApprovalLink(Payment approvedPayment){
    List<Links> links = approvedPayment.getLinks();
    String approvalLink = null;
    for (Links link : links) {
        if (link.getRel().equalsIgnoreCase("approval_url")) {
            approvalLink = link.getHref();
            break;
        }
    }
    return approvalLink;
}


}