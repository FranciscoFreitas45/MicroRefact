package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Exchange;
import pl.szymanski.sharelibrary.entity.UserBook;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;
@AllArgsConstructor
@Data
public class ExchangeResponse {

 private  Long id;

 private  ExchangeStatus exchangeStatus;

 private  Double deposit;

 private  Double distance;

 private  UserBookResponse book;

 private  BaseUserResponse user;

 private  CoordinatesResponse coordinates;


public ExchangeResponse of(Exchange exchange,double distance){
    return new ExchangeResponse(exchange.getId(), exchange.getExchangeStatus(), exchange.getDeposit(), distance, UserBookResponse.of(exchange.getUser().getBooks().stream().filter(it -> it.getBook().getId().equals(exchange.getBook().getId())).findFirst().orElse(new UserBook(null, null, null, null))), BaseUserResponse.of(exchange.getUser()), CoordinatesResponse.of(exchange.getCoordinates()));
}


}