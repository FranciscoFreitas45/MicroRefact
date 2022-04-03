package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TransactionDateController {

 private TransactionDate transactiondate;


@GetMapping
("/toString")
public String toString(){
  return transactiondate.toString();
}


}