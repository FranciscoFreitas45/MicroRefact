package pl.szymanski.sharelibrary.utils.generator;
 import pl.szymanski.sharelibrary.entity.Category;
import pl.szymanski.sharelibrary.entity.Exchange;
import pl.szymanski.sharelibrary.entity.Language;
import pl.szymanski.sharelibrary.enums.BookCondition;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;
import pl.szymanski.sharelibrary.requests.AddExchangeRequest;
import pl.szymanski.sharelibrary.requests.ExecuteExchangeRequest;
import pl.szymanski.sharelibrary.response.ExchangeResponse;
import java.util.ArrayList;
import java.util.List;
import pl.szymanski.sharelibrary.utils.constant.ExchangeConstant;
public class ExchangeGenerator {


public List<Exchange> getExchangeList(){
    Exchange first = getExchange();
    Exchange second = getExchange();
    List<Exchange> list = new ArrayList<>();
    list.add(first);
    Language language = new Language();
    language.setId(2);
    language.setName("Polish");
    second.getBook().setLanguage(language);
    Category category = new Category();
    category.setId(2);
    category.setName("Biography");
    second.getBook().setCategories(List.of(category));
    second.getBook().setCondition(BookCondition.BAD);
    list.add(second);
    return list;
}


public AddExchangeRequest getAddExchangeRequest(){
    return new AddExchangeRequest(TEST_EXCHANGE_DEPOSIT, TEST_EXCHANGE_BOOK_ID, TEST_EXCHANGE_USER_ID, CoordinatesGenerator.getCoordinatesRequest());
}


public Exchange getExchange(){
    Exchange exchange = new Exchange();
    exchange.setExchangeStatus(ExchangeStatus.STARTED);
    exchange.setUser(UserGenerator.getUserWithBooks());
    exchange.setBook(BookGenerator.getBook());
    exchange.setDeposit(TEST_EXCHANGE_DEPOSIT);
    exchange.setCoordinates(CoordinatesGenerator.getCoordinates());
    exchange.setId(TEST_EXCHANGE_ID);
    exchange.setForBook(BookGenerator.getBook());
    exchange.setWithUser(UserGenerator.getUserWithBooks());
    return exchange;
}


public ExchangeResponse getExchangeResponse(){
    return ExchangeResponse.of(getExchange());
}


public ExecuteExchangeRequest getExecuteExchangeRequest(){
    return new ExecuteExchangeRequest(TEST_EXCHANGE_ID, TEST_EXCHANGE_USER_ID, TEST_EXCHANGE_BOOK_ID);
}


}