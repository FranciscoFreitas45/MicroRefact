package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LabelsLookupController {

 private LabelsLookup labelslookup;

 private LabelsLookup labelslookup;


@GetMapping
("/retrieveExistingValues")
public LookupValuesList retrieveExistingValues(@RequestParam(name = "linkId") int linkId){
  return labelslookup.retrieveExistingValues(linkId);
}


}