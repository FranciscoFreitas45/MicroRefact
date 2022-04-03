package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TransactionServiceController {

 private TransactionService transactionservice;


@GetMapping
("/transferBetweenCashAccounts")
public TransferOutDTO transferBetweenCashAccounts(@RequestParam(name = "transferDTO") TransferDTO transferDTO){
  return transactionservice.transferBetweenCashAccounts(transferDTO);
}


}