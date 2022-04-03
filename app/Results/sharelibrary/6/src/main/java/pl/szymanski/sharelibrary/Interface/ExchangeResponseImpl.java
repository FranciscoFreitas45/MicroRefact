package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.ExchangeResponse;
public class ExchangeResponseImpl implements ExchangeResponse{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public ExchangeResponse of(Exchange exchange,double distance){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("exchange",exchange)
    .queryParam("distance",distance)
;  ExchangeResponse aux = restTemplate.getForObject(builder.toUriString(), ExchangeResponse.class);

 return aux;
}


}