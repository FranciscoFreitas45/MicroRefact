package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ExchangeServiceController {

 private ExchangeService exchangeservice;


@GetMapping
("/getExchangeById")
public Exchange getExchangeById(@RequestParam(name = "id") Long id){
  return exchangeservice.getExchangeById(id);
}


@GetMapping
("/getExchangesByUserId")
public List<Exchange> getExchangesByUserId(@RequestParam(name = "userId") Long userId){
  return exchangeservice.getExchangesByUserId(userId);
}


}