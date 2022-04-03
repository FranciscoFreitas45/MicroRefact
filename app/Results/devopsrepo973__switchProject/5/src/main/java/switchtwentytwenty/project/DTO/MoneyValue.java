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
public MoneyValue subtract(MoneyValue moneyValue){
    return new MoneyValue(this.value.subtract(moneyValue.value));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/subtract"))

.queryParam("moneyValue",moneyValue)
;
MoneyValue aux = restTemplate.getForObject(builder.toUriString(),MoneyValue.class);
return aux;
}


public double toDouble(){
    return this.value.doubleValue();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDouble"))

;
double aux = restTemplate.getForObject(builder.toUriString(),double.class);
return aux;
}


}