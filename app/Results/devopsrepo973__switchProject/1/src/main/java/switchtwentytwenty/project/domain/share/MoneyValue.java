package switchtwentytwenty.project.domain.share;
 import switchtwentytwenty.project.util.Util;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
public class MoneyValue {

 private  BigDecimal value;

 private  Currency currency;

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
public int compare(MoneyValue moneyValue){
    return this.value.compareTo(moneyValue.value);
}


public MoneyValue toNegative(){
    if (!this.isPositiveOrZero()) {
        throw new IllegalArgumentException("This value is already negative.");
    }
    return new MoneyValue(this.value.negate());
}


public double toDouble(){
    return this.value.doubleValue();
}


@Override
public int hashCode(){
    return Objects.hash(value, currency);
}


public MoneyValue subtract(MoneyValue moneyValue){
    return new MoneyValue(this.value.subtract(moneyValue.value));
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    MoneyValue that = (MoneyValue) o;
    return Objects.equals(value, that.value) && Objects.equals(currency, that.currency);
}


public boolean isPositiveOrZero(){
    BigDecimal zero = new BigDecimal(0);
    return this.value.compareTo(zero) >= 0;
}


public float floatValue(){
    return this.value.floatValue();
}


public MoneyValue sum(MoneyValue moneyValue){
    return new MoneyValue(this.value.add(moneyValue.value));
}


@Override
public String toString(){
    return value.toString();
}


}