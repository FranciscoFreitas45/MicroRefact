package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.dto.indto.TransferInDTO;
import switchtwentytwenty.project.dto.outdto.TransferOutDTO;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.TransactionService;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTOMapper;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.transaction.ITransferBetweenMembersCashController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class TransferBetweenTwoCashAccountsController implements ITransferBetweenMembersCashController{

@Autowired
 private  TransactionService transactionService;


@PostMapping(value = "/accounts/{accountID}/transfer/{accountID}")
public ResponseEntity<Object> transferBetweenTwoCashAccounts(TransferInDTO info){
    TransferOutDTO result;
    try {
        TransferDTO transferDTO = TransferDTOMapper.mapToTransferDTO(info);
        result = transactionService.transferBetweenCashAccounts(transferDTO);
        Link linkToTransfer = linkTo(TransferBetweenTwoCashAccountsController.class).slash("transfer").slash(result.getAmount()).withRel("memberAndMember");
        result.add(linkToTransfer);
        return new ResponseEntity<>("The transfer occurred successively", HttpStatus.CREATED);
    } catch (IllegalArgumentException | AccountNotCreatedException | InstantiationException | IOException | InvalidRelationTypeException | InvalidDateException | InvalidVATException | InvalidPersonNameException | NullPointerException | DepositNotPossibleException | WithdrawNotPossibleException | ElementNotFoundException | InvalidMovementTypeException | InvalidEmailException | ParseException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
        BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}


}