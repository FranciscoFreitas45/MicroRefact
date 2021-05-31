import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhCreditDetailServiceController {

 private PhCreditDetailService phcreditdetailservice;


@GetMapping
("/save")
public PhCreditDetail save(@RequestParam(name = "phCreditDetail") PhCreditDetail phCreditDetail){
  return phcreditdetailservice.save(phCreditDetail);
}


}