package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Box;
@Component
@Transactional
public class BoxToStringConverter implements Converter<Box, String>{


@Override
public String convert(Box box){
    String result;
    if (box == null) {
        result = null;
    } else {
        result = String.valueOf(box.getId());
    }
    return result;
}


}