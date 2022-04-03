package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Brotherhood;
@Component
@Transactional
public class BrotherhoodToStringConverter implements Converter<Brotherhood, String>{


@Override
public String convert(Brotherhood brotherhood){
    String result;
    if (brotherhood == null)
        result = null;
    else
        result = String.valueOf(brotherhood.getId());
    return result;
}


}