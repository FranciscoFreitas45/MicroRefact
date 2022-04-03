package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ILedgerIDGeneratorController {

 private ILedgerIDGenerator iledgeridgenerator;


@GetMapping
("/generate")
public LedgerID generate(){
  return iledgeridgenerator.generate();
}


}