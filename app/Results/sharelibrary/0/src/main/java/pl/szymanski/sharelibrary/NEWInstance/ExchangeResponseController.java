package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ExchangeResponseController {

 private ExchangeResponse exchangeresponse;


@GetMapping
("/of")
public ExchangeResponse of(@RequestParam(name = "exchange") Exchange exchange,@RequestParam(name = "distance") double distance){
  return exchangeresponse.of(exchange,distance);
}


}