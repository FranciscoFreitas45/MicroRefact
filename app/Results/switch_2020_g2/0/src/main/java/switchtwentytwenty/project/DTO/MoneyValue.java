package switchtwentytwenty.project.DTO;
 import switchtwentytwenty.project.util.Util;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
public class MoneyValue {

 private  BigDecimal value;

 private  Currency currency;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

// Constructor methods
/**
 * Constructor
 *
 * @param value - value to be stored
 */
public MoneyValue(BigDecimal value) {
    this.value = value;
    String[] defaultCurrencyCouple = Util.getSystemDefaultCurrency();
    this.currency = Currency.getInstance(new Locale(defaultCurrencyCouple[0], defaultCurrencyCouple[1]));
}
public boolean isPositiveOrZero(){
    BigDecimal zero = new BigDecimal(0);
    return this.value.compareTo(zero) >= 0;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isPositiveOrZero"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public MoneyValue sum(MoneyValue moneyValue){
    return new MoneyValue(this.value.add(moneyValue.value));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sum"))

.queryParam("moneyValue",moneyValue)
;
MoneyValue aux = restTemplate.getForObject(builder.toUriString(),MoneyValue.class);
return aux;
}


public int compare(MoneyValue moneyValue){
    return this.value.compareTo(moneyValue.value);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/compare"))

.queryParam("moneyValue",moneyValue)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


public MoneyValue subtract(MoneyValue moneyValue){
    return new MoneyValue(this.value.subtract(moneyValue.value));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/subtract"))

.queryParam("moneyValue",moneyValue)
;
MoneyValue aux = restTemplate.getForObject(builder.toUriString(),MoneyValue.class);
return aux;
}


}