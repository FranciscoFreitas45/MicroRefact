package switchtwentytwenty.project.applicationservice.appservice.iappservice;
 import switchtwentytwenty.project.dto.toservicedto.PaymentDTO;
import switchtwentytwenty.project.dto.outdto.PaymentOutDTO;
import switchtwentytwenty.project.dto.outdto.TransferOutDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.exception;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
public interface ITransactionService {


public TransferOutDTO transferBetweenCashAccounts(TransferDTO transferDTO)
;

public PaymentOutDTO addPaymentTransaction(PaymentDTO dto,String user)
;

}