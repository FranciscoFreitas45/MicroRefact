package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.dto.indto.PaymentInDTO;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ITransactionService;
import switchtwentytwenty.project.dto.toservicedto.PaymentDTO;
import switchtwentytwenty.project.dto.toservicedto.PaymentDTOMapper;
import switchtwentytwenty.project.dto.outdto.PaymentOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.transaction.IAddPaymentTransactionController;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.account.GetCashAccountBalanceController;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class AddPaymentTransactionController implements IAddPaymentTransactionController{

@Autowired
 private  ITransactionService transactionService;


@PostMapping(value = "/accounts/{accountID}/payment")
public ResponseEntity<Object> addPaymentTransaction(HttpServletRequest request,String accountID,PaymentInDTO dto){
    try {
        PaymentOutDTO payment;
        PaymentDTO newDTO = PaymentDTOMapper.mapToDTO(dto, accountID);
        String user = request.getUserPrincipal().getName();
        payment = this.transactionService.addPaymentTransaction(newDTO, user);
        Link linkToPayment = linkTo(AddPaymentTransactionController.class).slash("addpayment").slash("payment").withRel(payment.getDesignation());
        Link linkToAccountInfo = linkTo(methodOn(GetCashAccountBalanceController.class).getCashAccountBalance(dto.getPersonID(), accountID)).withRel("cash account balance");
        payment.add(linkToPayment);
        payment.add(linkToAccountInfo);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    } catch (InvalidPersonNameException | InvalidEmailException | InvalidVATException | InvalidDateException | AccountNotCreatedException | ElementNotFoundException | InstantiationException | ParseException | WithdrawNotPossibleException | InvalidMovementTypeException | UnsupportedOperationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException | UserEmailNotFoundException exception) {
        BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
        // httpstatus -> ver qual melhor value.
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}


}