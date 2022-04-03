package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BankStatementReconciliationViewFactoryController {

 private BankStatementReconciliationViewFactory bankstatementreconciliationviewfactory;


@GetMapping
("/createView")
public BankStatementReconciliationView createView(@RequestParam(name = "request") BanksStatementReconciliationViewCreateRequest request){
  return bankstatementreconciliationviewfactory.createView(request);
}


}