package pl.szymanski.sharelibrary.DTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Exchange;
import pl.szymanski.sharelibrary.entity.UserBook;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;
import pl.szymanski.sharelibrary.Interface.UserBookResponse;
import pl.szymanski.sharelibrary.Interface.BaseUserResponse;
import pl.szymanski.sharelibrary.Interface.CoordinatesResponse;
import pl.szymanski.sharelibrary.DTO.UserBookResponse;
import pl.szymanski.sharelibrary.DTO.BaseUserResponse;
import pl.szymanski.sharelibrary.DTO.CoordinatesResponse;
public class ExchangeResponse {

 private  Long id;

 private  ExchangeStatus exchangeStatus;

 private  Double deposit;

 private  Double distance;

 private  UserBookResponse book;

 private  BaseUserResponse user;

 private  CoordinatesResponse coordinates;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public ExchangeResponse of(Exchange exchange,double distance){
    return new ExchangeResponse(exchange.getId(), exchange.getExchangeStatus(), exchange.getDeposit(), distance, UserBookResponse.of(exchange.getUser().getBooks().stream().filter(it -> it.getBook().getId().equals(exchange.getBook().getId())).findFirst().orElse(new UserBook(null, null, null, null))), BaseUserResponse.of(exchange.getUser()), CoordinatesResponse.of(exchange.getCoordinates()));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("exchange",exchange)
.queryParam("distance",distance)
;
ExchangeResponse aux = restTemplate.getForObject(builder.toUriString(),ExchangeResponse.class);
return aux;
}


}