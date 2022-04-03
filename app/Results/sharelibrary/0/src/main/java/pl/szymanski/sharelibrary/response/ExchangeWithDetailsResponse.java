package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Exchange;
import pl.szymanski.sharelibrary.entity.UserBook;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;
@AllArgsConstructor
@Data
public class ExchangeWithDetailsResponse {

 private  Long id;

 private  ExchangeStatus exchangeStatus;

 private  Double deposit;

 private  Double distance;

 private  UserBookResponse book;

 private  BaseUserResponse user;

 private  CoordinatesResponse coordinates;

 private  BaseUserResponse withUser;

 private  BookWithoutUsersResponse forBook;


public ExchangeWithDetailsResponse of(Exchange exchange){
    return new ExchangeWithDetailsResponse(exchange.getId(), exchange.getExchangeStatus(), exchange.getDeposit(), null, UserBookResponse.of(exchange.getUser().getBooks().stream().filter(it -> it.getBook().getId().equals(exchange.getBook().getId())).findFirst().orElse(new UserBook(null, null, null, null))), BaseUserResponse.of(exchange.getUser()), CoordinatesResponse.of(exchange.getCoordinates()), (exchange.getWithUser() != null) ? BaseUserResponse.of(exchange.getWithUser()) : new BaseUserResponse(), (exchange.getForBook() != null) ? BookWithoutUsersResponse.of(exchange.getForBook()) : new BookWithoutUsersResponse());
}


}