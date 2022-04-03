package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Proclaim;
@Component
@Transactional
public class ProclaimToStringConverter implements Converter<Proclaim, String>{


@Override
public String convert(Proclaim proclaim){
    String result;
    if (proclaim == null)
        result = null;
    else
        result = String.valueOf(proclaim.getId());
    return result;
}


}