package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Float;
@Component
@Transactional
public class FloatToStringConverter implements Converter<Float, String>{


@Override
public String convert(Float floatt){
    String result;
    if (floatt == null)
        result = null;
    else
        result = String.valueOf(floatt.getId());
    return result;
}


}