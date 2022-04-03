package converters;
 import java.net.URLEncoder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.CreditCard;
@Component
@Transactional
public class CreditCardToStringConverter implements Converter<CreditCard, String>{


@Override
public String convert(CreditCard creditCard){
    String result;
    StringBuilder builder;
    if (creditCard == null) {
        result = null;
    } else {
        try {
            builder = new StringBuilder();
            builder.append(URLEncoder.encode(creditCard.getHolderName(), "UTF-8"));
            builder.append("|");
            builder.append(URLEncoder.encode(creditCard.getBrandName(), "UTF-8"));
            builder.append("|");
            builder.append(URLEncoder.encode(creditCard.getNumber().toString(), "UTF-8"));
            builder.append("|");
            builder.append(URLEncoder.encode(creditCard.getExpirationMonth().toString(), "UTF-8"));
            builder.append("|");
            builder.append(URLEncoder.encode(creditCard.getExpirationYear().toString(), "UTF-8"));
            builder.append("|");
            builder.append(URLEncoder.encode(creditCard.getCvvCode().toString(), "UTF-8"));
            result = builder.toString();
        } catch (final Throwable oops) {
            throw new RuntimeException(oops);
        }
    }
    return result;
}


}