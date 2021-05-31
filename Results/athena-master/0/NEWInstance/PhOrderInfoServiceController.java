import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhOrderInfoServiceController {

 private PhOrderInfoService phorderinfoservice;


@GetMapping
("/generateOrderNo")
public String generateOrderNo(@RequestParam(name = "prefix") String prefix){
  return phorderinfoservice.generateOrderNo(prefix);
}


}