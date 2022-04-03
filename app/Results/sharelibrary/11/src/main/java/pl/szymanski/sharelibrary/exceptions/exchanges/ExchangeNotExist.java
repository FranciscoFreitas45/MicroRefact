package pl.szymanski.sharelibrary.exceptions.exchanges;
 import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
public class ExchangeNotExist extends RuntimeException{

public ExchangeNotExist(Long exchangeId) {
    super(String.format(ExceptionMessages.EXCHANGE_DOES_NOT_EXIST_FORMAT, exchangeId));
}
}