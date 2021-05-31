import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class IStockServiceController {

 private IStockService istockservice;


@GetMapping
("/findByStockType")
public List<DailyNecessaryDto> findByStockType(){
  return istockservice.findByStockType();
}


}